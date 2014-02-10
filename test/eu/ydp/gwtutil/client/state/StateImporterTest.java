package eu.ydp.gwtutil.client.state;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.state.converter.IStateConvertionStrategy;
import eu.ydp.gwtutil.client.state.converter.StateConverter;
import eu.ydp.gwtutil.json.YNativeJsonFactory;

@RunWith(JUnitParamsRunner.class)
public class StateImporterTest {

	@Test
	public void testSetStateConverters() {
		// given
		StateImporter stateImporter = new StateImporter();
		// when
		stateImporter.setStateConverters(getConverters());

		// then
		List<StateConverter> converters = stateImporter.stateConverters;
		for (int i = 0; i < converters.size(); i++) {
			assertThat(converters.get(i).getVersion(), equalTo(i));
		}
	}

	@Test
	@Parameters(method = "importStateParams")
	public void testImportState(int fromVersion, int toVersion) {
		// given
		List<YJsonValue> states = prepareJsonStates();
		List<StateConverter> converters = prepareStateConverterList(states);
		StateImporter stateImporter = new StateImporter();
		stateImporter.setStateConverters(converters);

		// when
		YJsonValue importedState = stateImporter.importState(states.get(fromVersion), toVersion);

		// then
		if (fromVersion != toVersion) {
			YJsonObject convertedState = (YJsonObject) importedState;
			assertThat(convertedState.get(StateVersion.VERSION_FIELD).isNumber().numberValue().intValue(), equalTo(toVersion));
			assertThat(convertedState.get("key" + toVersion).isNumber().numberValue().intValue(), equalTo(toVersion));
		} else {
			assertThat(importedState, equalTo(states.get(toVersion)));
		}

	}

	@SuppressWarnings("unused")
	private Object[] importStateParams() {
		return $($(0, 0), $(0, 3), $(2, 3), $(1, 2), $(1, 3), $(0, 1), $(0, 2), $(3, 3), $(2, 2));
	}

	private List<YJsonValue> prepareJsonStates() {
		List<YJsonValue> states = new ArrayList<YJsonValue>();

		for (int i = 0; i < 4; i++) {
			YJsonObject state = YNativeJsonFactory.createObject();
			state.put("key" + i, YNativeJsonFactory.createNumber(i));
			if (i > 0) {
				state.put(StateVersion.VERSION_FIELD, YNativeJsonFactory.createNumber(i));
			}
			states.add(state);
		}

		return states;
	}

	private List<StateConverter> prepareStateConverterList(List<YJsonValue> states) {
		List<StateConverter> converters = new ArrayList<StateConverter>();
		for (int i = 0; i < states.size() - 1; i++) {
			converters.add(prepareStateConverter(i, states.get(i), states.get(i + 1)));
		}

		return converters;
	}

	@Test
	@Parameters(method = "stateVersionParams")
	public void testGetStateVersion(YJsonValue state, int expectedVersion) {
		// given
		StateImporter stateImporter = new StateImporter();

		// when
		int version = stateImporter.getStateVersion(state);

		// then
		assertThat(version, equalTo(expectedVersion));

	}

	@SuppressWarnings("unused")
	private Object[] stateVersionParams() {
		YJsonValue stateArr = YNativeJsonFactory.createArray();
		stateArr.isArray().set(0, YNativeJsonFactory.createString("a"));

		YJsonValue stateObj = YNativeJsonFactory.createObject();
		stateObj.isObject().put(StateVersion.VERSION_FIELD, YNativeJsonFactory.createNumber(1));

		YJsonValue stateObjNoVersion = YNativeJsonFactory.createObject();

		return $($(stateArr, 0), $(stateObj, 1), $(stateObjNoVersion, 0));
	}

	private List<StateConverter> getConverters() {
		List<StateConverter> converters = new ArrayList<StateConverter>();

		converters.add(prepareStateConverter(2, null, null));
		converters.add(prepareStateConverter(0, null, null));
		converters.add(prepareStateConverter(1, null, null));

		return converters;
	}

	private StateConverter prepareStateConverter(int version, YJsonValue inState, YJsonValue outState) {
		IStateConvertionStrategy strategy = mock(IStateConvertionStrategy.class);
		when(strategy.getStartVersion()).thenReturn(version);
		when(strategy.convert(inState)).thenReturn(outState);

		return new StateConverter(strategy);
	}

}

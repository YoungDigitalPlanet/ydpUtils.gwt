package eu.ydp.gwtutil.client.state;

import static junitparams.JUnitParamsRunner.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.AbstractTestBase;
import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonNumber;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.state.converter.StateConverter;
import eu.ydp.gwtutil.json.YNativeJsonFactory;

@RunWith(JUnitParamsRunner.class)
public class AbstractStateHelperTest extends AbstractTestBase {

	private IJSONService jsonService;

	@Override
	public void setUp() {

		super.setUp();
		jsonService = injector.getInstance(IJSONService.class);
	}

	@Test
	public void testImportState() {
		// given
		AbstractStateHelper helper = mock(AbstractStateHelper.class);
		YJsonValue inState = YNativeJsonFactory.createObject();
		when(helper.importState(inState)).thenCallRealMethod();
		when(helper.prepareStateConverters()).thenReturn(new ArrayList<StateConverter>());

		// when
		YJsonValue outState = helper.importState(inState);

		// then
		assertThat(inState, equalTo(outState));

	}

	@Test
	@Parameters(method = "exportStateParams")
	public void testExportState(YJsonValue inState) {
		// given
		AbstractStateHelper helper = mock(AbstractStateHelper.class);
		helper.jsonService = jsonService;
		when(helper.exportState(inState)).thenCallRealMethod();
		when(helper.getTargetVersion()).thenReturn(0);

		// when
		YJsonValue exportedState = helper.exportState(inState);

		// then
		if (inState instanceof YJsonArray) {
			assertThat(exportedState, equalTo(inState));
		} else {
			YJsonNumber version = exportedState.isObject().get(StateVersion.VERSION_FIELD).isNumber();
			assertThat(version.numberValue().intValue(), equalTo(helper.getTargetVersion()));
		}

	}

	@SuppressWarnings("unused")
	private Object[] exportStateParams() {
		return $($(YNativeJsonFactory.createObject()), $(YNativeJsonFactory.createArray()));
	}

}

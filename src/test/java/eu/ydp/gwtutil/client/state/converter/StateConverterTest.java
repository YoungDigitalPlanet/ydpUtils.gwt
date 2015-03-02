package eu.ydp.gwtutil.client.state.converter;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.AbstractTestBase;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;

@RunWith(JUnitParamsRunner.class)
public class StateConverterTest extends AbstractTestBase {

	IJSONService jsonService;

	@Override
	public void setUp() {
		super.setUp();
		jsonService = injector.getInstance(IJSONService.class);
	}

	@Test
	public void testGetVersion() {
		// given
		int version = 1;
		IStateConvertionStrategy strategy = prepareVesrionStrategy(version);
		StateConverter converter = new StateConverter(strategy);

		// when
		int convVersion = converter.getVersion();
		// then
		assertThat(convVersion, equalTo(version));
	}

	@Test
	public void testConvert() {
		// given
		IStateConvertionStrategy strategy = mock(IStateConvertionStrategy.class);
		YJsonValue inState = jsonService.createString("aaaa");
		YJsonValue outState = jsonService.createString("bbb");
		when(strategy.convert(inState)).thenReturn(outState);

		StateConverter converter = new StateConverter(strategy);

		// when
		YJsonValue convertedState = converter.convert(inState);
		// then
		assertThat(convertedState, equalTo(outState));

	}

	@Test
	@Parameters(method = "compareToParams")
	public void testCompareTo(int version0, int version1, int result) {
		// given
		StateConverter converter0 = new StateConverter(prepareVesrionStrategy(version0));
		StateConverter converter1 = new StateConverter(prepareVesrionStrategy(version1));

		// when
		int comparison = converter0.compareTo(converter1);

		// then
		assertThat(comparison, equalTo(result));
	}

	@SuppressWarnings("unused")
	private Object[] compareToParams() {
		return $($(1, 1, 0), $(1, 10, -1), $(10, 1, 1));
	}

	private IStateConvertionStrategy prepareVesrionStrategy(int version) {
		IStateConvertionStrategy strategy = mock(IStateConvertionStrategy.class);
		when(strategy.getStartVersion()).thenReturn(version);

		return strategy;
	}

}

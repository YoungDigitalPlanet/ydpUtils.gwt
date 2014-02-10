package eu.ydp.gwtutil.junit.runners;

import java.util.Arrays;
import java.util.List;

public abstract class ParameterizedMethodsRunnerTestBase {

	protected static List<Object[]> params = Arrays.asList(new Object[][] { { 0, "a" }, { 1, "b" }, { 1, "c" } });

	protected final static String VISIT_ONCE_MESSAGE = "Each parameterized method should be visited only once with each parameters set";
}

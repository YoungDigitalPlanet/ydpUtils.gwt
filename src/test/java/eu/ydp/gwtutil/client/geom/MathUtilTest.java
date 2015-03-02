package eu.ydp.gwtutil.client.geom;

import eu.ydp.gwtutil.AbstractTestBase;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class MathUtilTest extends AbstractTestBase {

	public Object[] data() {
		return $(
				$(0d, 0d, 0d, 0d, 0d),
				$(0d, 0d, 100d, 0d, 100d),
				$(0d, 0d, 300d, 400d, 500d),
				$(0d, 0d, -300d, -400d, 500d),
				$(-200d, -200d, 100d, 200d, 500d)
		);
	}

	@Test
	@Parameters(method = "data")
	public void distance(double x1, double y1, double x2, double y2, double result) {
		assertThat(MathUtil.distance(x1, y1, x2, y2), is(result));
	}
}

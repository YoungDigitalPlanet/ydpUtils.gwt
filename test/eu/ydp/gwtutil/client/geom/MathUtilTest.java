package eu.ydp.gwtutil.client.geom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.AbstractTestBase;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner.MethodParameters;

@RunWith(ParameterizedMethodsRunner.class)
public class MathUtilTest extends AbstractTestBase {

	@MethodParameters(forMethod="distance", name="{index}: |{0},{1}| |{2},{3}| -> {4}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{0d, 0d, 0d, 0d, 0d},
				{0d, 0d, 100d, 0d, 100d},
				{0d, 0d, 300d, 400d, 500d},
				{0d, 0d, -300d, -400d, 500d},
				{-200d, -200d, 100d, 200d, 500d},
				});
	}
	
	@Test
	public void distance(double x1, double y1, double x2, double y2, double result){
		assertThat(MathUtil.distance(x1,y1, x2, y2), is(result));
	}
}

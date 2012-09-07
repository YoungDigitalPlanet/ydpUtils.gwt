package eu.ydp.gwtutil.junit.runners;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.client.collections.CollectionsUtil;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner.Parameter;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner.Parameters;

@RunWith(ParameterizedMethodsRunner.class)
public class ParameterizedMethodsRunnerFieldsTest extends ParameterizedMethodsRunnerTestBase {

	private static List<Boolean> paramsUsed1 = CollectionsUtil.fillList(false, params.size());
	private static List<Boolean> paramsUsed2 = CollectionsUtil.fillList(false, params.size());
	
	@Parameter(0)
	public Integer x;
	@Parameter(1)
	public String y;
	
	@Parameters(name="{index} - {0}, {1}")
	public static Collection<Object[]> parameterizedParameters(){
		return params;
	}
	
	@Test
	public void parameterizedMethod1(){
		for (int i = 0 ; i < params.size() ; i ++){
			Object[] currParams = params.get(i);
			if (!paramsUsed1.get(i)){
				if (x == currParams[0]  &&  y.equals(currParams[1])){
					paramsUsed1.set(i, true);
					return;
				}
			}
		}
		Assert.fail(VISIT_ONCE_MESSAGE);
	}
	
	@Test
	public void parameterizedMethod2(){
		for (int i = 0 ; i < params.size() ; i ++){
			Object[] currParams = params.get(i);
			if (!paramsUsed2.get(i)){
				if (x == currParams[0]  &&  y.equals(currParams[1])){
					paramsUsed2.set(i, true);
					return;
				}
			}
		}
		Assert.fail(VISIT_ONCE_MESSAGE);
	}
	
	@AfterClass
	public static void after(){
		assertThat(paramsUsed1.contains(false), is(false));
		assertThat(paramsUsed2.contains(false), is(false));
	}
}

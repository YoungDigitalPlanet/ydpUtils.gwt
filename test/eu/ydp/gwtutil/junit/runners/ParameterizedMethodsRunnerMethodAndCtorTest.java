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
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner.MethodParameters;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner.Parameters;

@RunWith(ParameterizedMethodsRunner.class)
public class ParameterizedMethodsRunnerMethodAndCtorTest extends ParameterizedMethodsRunnerTestBase {

	private static List<Boolean> paramsUsed1 = CollectionsUtil.fillList(false, params.size());
	private static List<Boolean> paramsUsed2 = CollectionsUtil.fillList(false, params.size());
	private static List<Boolean> paramsUsed3 = CollectionsUtil.fillList(false, params.size());
	private static List<Boolean> paramsUsed4 = CollectionsUtil.fillList(false, params.size());
	
	private Integer x;
	private String y;
	
	
	public ParameterizedMethodsRunnerMethodAndCtorTest(Integer x, String y){
		this.x = x;
		this.y = y;
	}

	@Parameters(name="{index} - {0}, {1}")
	public static Collection<Object[]> parameterizedMethodCtorParameters(){
		return params;
	}
	
	@MethodParameters(forMethod="parameterizedMethod1", name="{index} - {0}, {1}")
	public static Collection<Object[]> parameterizedMethod1Parameters(){
		return params;
	}
	
	@MethodParameters(forMethod="parameterizedMethod2", name="{index} - {0}, {1}")
	public static Collection<Object[]> parameterizedMethod2Parameters(){
		return params;
	}
	
	@Test
	public void parameterizedMethod1(Integer x, String y){
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
	public void parameterizedMethod2(Integer x, String y){
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
	
	@Test
	public void parameterizedMethod3(){
		for (int i = 0 ; i < params.size() ; i ++){
			Object[] currParams = params.get(i);
			if (!paramsUsed3.get(i)){
				if (x == currParams[0]  &&  y.equals(currParams[1])){
					paramsUsed3.set(i, true);
					return;
				}
			}
		}
		Assert.fail(VISIT_ONCE_MESSAGE);
	}
	
	@Test
	public void parameterizedMethod4(){
		for (int i = 0 ; i < params.size() ; i ++){
			Object[] currParams = params.get(i);
			if (!paramsUsed4.get(i)){
				if (x == currParams[0]  &&  y.equals(currParams[1])){
					paramsUsed4.set(i, true);
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
		assertThat(paramsUsed3.contains(false), is(false));
		assertThat(paramsUsed4.contains(false), is(false));
	}
	
}

package eu.ydp.gwtutil.user.rebind.constants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.AbstractTestBase;
import eu.ydp.gwtutil.client.collections.ListCreator;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner;
import eu.ydp.gwtutil.junit.runners.ParameterizedMethodsRunner.MethodParameters;

@RunWith(ParameterizedMethodsRunner.class)
public class StringConstantsGeneratorTest extends AbstractTestBase {

	private static final String SEPARATOR = "#";
	private StringConstantsGenerator generator;

	@Override
	public void setUp() {
		super.setUp();
		generator = new StringConstantsGenerator();
	}
	
	@MethodParameters(forMethod="camelCaseToComponents", name= "{index}: {0}")
 	public static Collection<Object[]> dataCamelCaseToComponents() {
 		return Arrays.asList(new Object[][] { 
 				{ "AbcDef12GhiJk", ListCreator.create("abc").add("def").add("12").add("ghi").add("jk").build() }, 
 				{ "123", ListCreator.create("123").build() }, 
 				{ "A", ListCreator.create("a").build() }, 
 				{ "ABC", ListCreator.create("a").add("b").add("c").build() }, 
 				{ "", new ArrayList<String>() }, 
 				});
 	}

	
	@Test
	public void camelCaseToComponents(String input, List<String> output){
		// when 
		List<String> actualOutput = generator.camelCaseToComponents(input);
		
		// then
		assertThat(actualOutput, equalTo(output));
	}
	
	@MethodParameters(forMethod="combineComponents", name= "{index}: {0}")
 	public static Collection<Object[]> dataCombineComponents() {
 		return Arrays.asList(new Object[][] { 
 				{ ListCreator.create("abc").add("def").add("12").add("ghi").add("jk").build(), String.format("abc%1$sdef%1$s12%1$sghi%1$sjk", SEPARATOR) }, 
 				{ ListCreator.create("123").build(), "123" }, 
 				{ new ArrayList<String>(), "" }, 
 				});
 	}
	
	@Test
	public void combineComponents(List<String> input, String output){
		// when 
		String actualOutput = generator.combineComponents(input, SEPARATOR);
		
		// then
		assertThat(actualOutput, equalTo(output));
		
	}
}

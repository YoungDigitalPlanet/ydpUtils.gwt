package eu.ydp.gwtutil.junit.runners;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * <p>Implementation based on junit org.junit.runners.Parameterized class.</p>
 * 
 * <p>
 * The custom runner <code>ParameterizedMethodsRunner</code> implements parameterized tests.
 * When running a parameterized test class, instances are created for the
 * cross-product of the test methods and the test data elements.
 * </p>
 * 
 * For example, to test a Fibonacci function, write:
 * 
 * <pre>
 * &#064;RunWith(ParameterizedMethodsRunner.class)
 * public class FibonacciTest {
 * 	&#064;Parameters(name= &quot;{index}: fib({0})={1}&quot;)
 * 	public static Collection&lt;Object[]&gt; data() {
 * 		return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 },
 * 				{ 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
 * 	}
 * 
 * 	&#064;MethodParameters(forMethod=&quot;testMethod&quot;, name= &quot;{index}: ({0}, {1}&quot;)
 * 	public static Collection&lt;Object[]&gt; data() {
 * 		return Arrays.asList(new Object[][] { { 1, 1 }, { 2, 2 }, { 5, 5 }, { 6, 6 } });1
 * 	}
 * 
 * 	private int fInput;
 * 
 * 	private int fExpected;
 * 
 * 	public FibonacciTest(int input, int expected) {
 * 		fInput= input;
 * 		fExpected= expected;
 * 	}
 * 
 * 	&#064;Test
 * 	public void test() {
 * 		assertEquals(fExpected, Fibonacci.compute(fInput));
 * 	}
 * 
 * 	&#064;Test
 * 	public void testMethod(int x, int y) {
 * 		assertEquals(x, y);
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * Each instance of <code>FibonacciTest</code> will be constructed using the
 * two-argument constructor and the data values in the
 * <code>&#064;Parameters</code> method.
 * 
 * <p>
 * In order that you can easily identify the individual tests, you may provide a
 * name for the <code>&#064;Parameters</code> and <code>&#064;MethodParameters</code> annotation. This name is allowed
 * to contain placeholders, which are replaced at runtime. The placeholders are
 * <dl>
 * <dt>{index}</dt>
 * <dd>the current parameter index</dd>
 * <dt>{0}</dt>
 * <dd>the first parameter value</dd>
 * <dt>{1}</dt>
 * <dd>the second parameter value</dd>
 * <dt>...</dt>
 * <dd></dd>
 * </dl>
 * In the example given above, the <code>ParameterizedMethodsRunner</code> runner creates
 * names like <code>[1: fib(3)=2]</code>. If you don't use the name parameter,
 * then the current parameter index is used as name.
 * </p>
 *
 * You can also write:
 *
 * <pre>
 * &#064;RunWith(ParameterizedMethodsRunner.class)
 * public class FibonacciTest {
 * 	&#064;Parameters
 * 	public static Collection&lt;Object[]&gt; data() {
 * 		return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 },
 * 				{ 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
 * 	}
 * 	&#064;Parameter(0)
 * 	public int fInput;
 *
 * 	&#064;Parameter(1)
 * 	public int fExpected;
 *
 * 	&#064;Test
 * 	public void test() {
 * 		assertEquals(fExpected, Fibonacci.compute(fInput));
 * 	}
 * }
 * </pre>
 *
 * <p>
 * Each instance of <code>FibonacciTest</code> will be constructed with the default constructor
 * and fields annotated by <code>&#064;Parameter</code>  will be initialized
 * with the data values in the <code>&#064;Parameters</code> method.
 * </p>
 * @since 4.0
 * 
 * @author Rafal Rybacki rrybacki@dyp.com.pl
 * 
 */
public class ParameterizedMethodsRunner extends Suite {
	/**
	 * Annotation for a method which provides parameters to be injected into the
	 * test class constructor by <code>Parameterized</code>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface MethodParameters {
		/**
		 * <p>
		 * Optional pattern to derive the test's name from the parameters. Use
		 * numbers in braces to refer to the parameters or the additional data
		 * as follows:
		 * </p>
		 * 
		 * <pre>
		 * {index} - the current parameter index
		 * {0} - the first parameter value
		 * {1} - the second parameter value
		 * etc...
		 * </pre>
		 * <p>
		 * Default value is "{index}" for compatibility with previous JUnit
		 * versions.
		 * </p>
		 * 
		 * @return {@link MessageFormat} pattern string, except the index
		 *         placeholder.
		 * @see MessageFormat
		 */
		String name() default "{index}";
		
		/**
		 * Name of the method to be parameterized.
		 */
		String[] forMethod();
	}
	
	/**
	 * Annotation for a method which provides parameters to be injected into the
	 * test class constructor by <code>Parameterized</code>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface Parameters {
		/**
		 * <p>
		 * Optional pattern to derive the test's name from the parameters. Use
		 * numbers in braces to refer to the parameters or the additional data
		 * as follows:
		 * </p>
		 * 
		 * <pre>
		 * {index} - the current parameter index
		 * {0} - the first parameter value
		 * {1} - the second parameter value
		 * etc...
		 * </pre>
		 * <p>
		 * Default value is "{index}" for compatibility with previous JUnit
		 * versions.
		 * </p>
		 * 
		 * @return {@link MessageFormat} pattern string, except the index
		 *         placeholder.
		 * @see MessageFormat
		 */
		String name() default "{index}";
	}

	/**
	 * Annotation for fields of the test class which will be initialized by the
	 * method annotated by <code>Parameters</code><br/>
	 * By using directly this annotation, the test class constructor isn't needed.<br/>
	 * Index range must start at 0.
	 * Default value is 0.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Parameter {
		/**
		 * Method that returns the index of the parameter in the array
		 * returned by the method annotated by <code>Parameters</code>.<br/>
		 * Index range must start at 0.
		 * Default value is 0.
		 * @return the index of the parameter.
		 */
		int value() default 0;
	}
	
	public class InvokeMethodWithParameters extends Statement {
		private final FrameworkMethod fTestMethod;
		private Object fTarget;
		private Object[] fParams;
		
		public InvokeMethodWithParameters(FrameworkMethod testMethod, Object target, Object[] params) {
			fTestMethod= testMethod;
			fTarget= target;
			fParams = params;
		}
		
		@Override
		public void evaluate() throws Throwable {
			fTestMethod.invokeExplosively(fTarget, fParams);
		}
	}

	private class TestClassRunnerForParameters extends BlockJUnit4ClassRunner {
		private final Object[] fParameters;

		private final String fName;

		private final String fForMethod;

		private Object[] fMethodParameters;

		private Collection<String> fIgnoreMethods;

		private boolean fIgnoreNameInTest;

		TestClassRunnerForParameters(Class<?> type, Object[] ctorParameters, Object[] methodParameters, String name, String forMethod) throws InitializationError {
			super(type);
			fParameters= ctorParameters;
			fName= name;
			fForMethod = forMethod;
			this.fMethodParameters = methodParameters;
			this.fIgnoreMethods = null;
			this.fIgnoreNameInTest = false;
		}

		TestClassRunnerForParameters(Class<?> type, Object[] ctorParameters, Object[] methodParameters, String name, Collection<String> ignoreMethods, boolean ignoreNameInTest) throws InitializationError {
			super(type);
			fParameters= ctorParameters;
			fName= name;
			fForMethod = null;
			this.fMethodParameters = methodParameters;
			this.fIgnoreMethods = ignoreMethods;
			this.fIgnoreNameInTest = ignoreNameInTest;
		}

		@Override
		public Object createTest() throws Exception {
			if (fieldsAreAnnotated()) {
				return createTestUsingFieldInjection();
			} else {
				return createTestUsingConstructorInjection();
			}
		}
		
		@Override
		protected List<FrameworkMethod> computeTestMethods() {
			List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Test.class);
			if (fIgnoreMethods == null){
				for (Iterator<FrameworkMethod> it = methods.iterator(); it.hasNext() ; ){
					if (!it.next().getName().equals(fForMethod)){
						it.remove();
					}
				}
			} else {
				for (String ignoredMethod : fIgnoreMethods){
					for (Iterator<FrameworkMethod> it = methods.iterator(); it.hasNext() ; ){
						if (it.next().getName().equals(ignoredMethod)){
							it.remove();
						}
					}
				}
			} 
			return methods;
		}
		
		@Override
		protected void collectInitializationErrors(List<Throwable> errors) {
			validateNoNonStaticInnerClass(errors);
			validateConstructor(errors);
			
			// TODO Extend the verification.
		}

		protected Statement methodInvoker(FrameworkMethod method, Object test) {
			return new InvokeMethodWithParameters(method, test, fMethodParameters);
		}

		@Override
		protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
			Description description= describeChild(method);
			if ((fForMethod == null  ||  method.getName().equals(fForMethod))  &&  (fIgnoreMethods == null  ||  !fIgnoreMethods.contains(method.getName()))){
				if (method.getAnnotation(Ignore.class) != null) {
					notifier.fireTestIgnored(description);
				} else {
					runLeaf(methodBlock(method), description, notifier);
				}
			} else {
				notifier.fireTestIgnored(description);
			}
		}
		
		private Object createTestUsingConstructorInjection() throws Exception {
			return getTestClass().getOnlyConstructor().newInstance(fParameters);
		}
		
		private Object createTestUsingFieldInjection() throws Exception {
			List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
			if (annotatedFieldsByParameter.size() != fParameters.length)
					throw new Exception("Wrong number of parameters and @Parameter fields."+
					" @Parameter fields counted: "+annotatedFieldsByParameter.size()+", available parameters: "+fParameters.length+".");
			Object testClassInstance = getTestClass().getJavaClass().newInstance();
			for (FrameworkField each : annotatedFieldsByParameter) {
				Field field = each.getField();
				Parameter annotation = field.getAnnotation(Parameter.class);
				int index = annotation.value();
				try {
					field.set(testClassInstance,  fParameters[index]);
				} catch(IllegalArgumentException iare) {
					throw new Exception(getTestClass().getName() + ": Trying to set "+field.getName()+
					" with the value "+fParameters[index]+
					" that is not the right type ("+fParameters[index].getClass().getSimpleName()+" instead of "+
					field.getType().getSimpleName()+").", iare);
				}
			}
			return testClassInstance;
		}

		@Override
		protected String getName() {
			return fName;
		}

		@Override
		protected String testName(FrameworkMethod method) {
			if (fIgnoreMethods != null){
				String output = method.getName();
				if (fIgnoreMethods.contains(method.getName())){
					output += " " + NOEXEC;
				}
				if (!fIgnoreNameInTest){
					output += " " + getName();
				}
				return output;
			}
			if (method.getName().equals(fForMethod)){
				return getName();	
			}
			return method.getName() + " " + NOEXEC + " " + getName();
		}

		@Override
		protected void validateConstructor(List<Throwable> errors) {
			validateOnlyOneConstructor(errors);
			if (fieldsAreAnnotated()) {
				validateZeroArgConstructor(errors);
			}
		}

		@Override
		protected Statement classBlock(RunNotifier notifier) {
			return childrenInvoker(notifier);
		}

		@Override
		protected Annotation[] getRunnerAnnotations() {
			return new Annotation[0];
		}
	}

	private static final List<Runner> NO_RUNNERS= Collections
			.<Runner> emptyList();
	
	private static final String NOEXEC = "NOEXEC";
	
	private static final String NO_PARAMS_INFO = "Execution of non-parameterized methods.";

	private final ArrayList<Runner> runners= new ArrayList<Runner>();

	/**
	 * Only called reflectively. Do not use programmatically.
	 */
	public ParameterizedMethodsRunner(Class<?> klass) throws Throwable {
		super(klass, NO_RUNNERS);
		Map<String, Collection<Object[]>> methodsPrameters = allMethodsParameters();
		Collection<Object[]> parameters = allParameters();
		createRunnersForParameters(methodsPrameters, parameters);
	}

	@Override
	protected List<Runner> getChildren() {
		return runners;
	}

	@SuppressWarnings("unchecked")
	private Collection<Object[]> allParameters() throws Throwable {
		FrameworkMethod method =  getParametersMethod();
		if (method != null){
			Object parameters= getParametersMethod().invokeExplosively(null);
			if (parameters instanceof Collection){
				return (Collection<Object[]>) parameters;
			}
		}
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[]{});
		return params;
	}

	private FrameworkMethod getParametersMethod() throws Exception {
		List<FrameworkMethod> methods= getTestClass().getAnnotatedMethods(
				Parameters.class);
		for (FrameworkMethod each : methods) {
			if ((each.getMethod().getModifiers() | Modifier.PUBLIC) > 0  &&  (each.getMethod().getModifiers() | Modifier.STATIC) > 0)
				return each;
		}
		return null;
	}
	
	private Map<String, Collection<Object[]>> allMethodsParameters() throws Throwable{
		Map<String, Collection<Object[]>> map = new HashMap<String, Collection<Object[]>>();
		List<FrameworkMethod> testMethods = getTestClass().getAnnotatedMethods(Test.class);
		for (FrameworkMethod testMethod : testMethods){
			String forMethod = testMethod.getName();
			map.put(forMethod, allMethodParameters(forMethod));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private Collection<Object[]> allMethodParameters(String forMethod) throws Throwable {
		FrameworkMethod method = getParametersMethod(forMethod);
		if (method != null){
			Object parameters= getParametersMethod(forMethod).invokeExplosively(null);
			if (parameters instanceof Iterable)
				return (Collection<Object[]>) parameters;
			else
				throw parametersMethodReturnedWrongType();
		}
		return new ArrayList<Object[]>();
	}

	private FrameworkMethod getParametersMethod(String forMethod) throws Exception {
		List<FrameworkMethod> methods= getTestClass().getAnnotatedMethods(MethodParameters.class);
		for (FrameworkMethod each : methods) {
			if ((each.getMethod().getModifiers() | Modifier.PUBLIC) > 0  &&  (each.getMethod().getModifiers() | Modifier.STATIC) > 0 && 
					Arrays.asList(each.getAnnotation(MethodParameters.class).forMethod()).contains(forMethod))
				return each;
		}
		return null;
	}

	private void createRunnersForParameters(Map<String, Collection<Object[]>> allMethodParameters, Collection<Object[]> allParameters) throws InitializationError, Exception {
		try {
			List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Test.class);

			List<String> parameterizedMethod = new ArrayList<String>();
			
			for (FrameworkMethod method : methods){
				
				if (allMethodParameters.get(method.getName()) != null  &&  allMethodParameters.get(method.getName()).iterator().hasNext()){
					parameterizedMethod.add(method.getName());
					int i= 0;
					Iterable<Object[]> ctorParams = allParameters;
					Iterable<Object[]> methodParams = allMethodParameters.get(method.getName());
					Object[] defaultCtorParams;
					if (ctorParams == null  ||  !ctorParams.iterator().hasNext()){
						defaultCtorParams = new Object[]{};
					} else {
						defaultCtorParams = ctorParams.iterator().next();
					}
				
					String namePattern = getNamePattern(method.getName());
					for (Object[] parametersOfSingleTest : methodParams) {
						String name= method.getName() + nameFor(namePattern, i, parametersOfSingleTest);
						TestClassRunnerForParameters runner= new TestClassRunnerForParameters(
								getTestClass().getJavaClass(), defaultCtorParams, parametersOfSingleTest,
								name, method.getName());
						runners.add(runner);
						++i;
					}
				}
			}
			
			if (parameterizedMethod.size() < methods.size()){
				int k = 0;
				
				Collection<Object[]> ctorParams = allParameters;
	
				String namePattern = getNamePattern();
				for (Object[] parametersOfSingleTest : ctorParams) {
					String name= nameFor(namePattern, k, parametersOfSingleTest);
					TestClassRunnerForParameters runner= new TestClassRunnerForParameters(
							getTestClass().getJavaClass(), parametersOfSingleTest, new Object[]{},
							name, parameterizedMethod, ctorParams.size() == 1);
					runners.add(runner);
					++k;
				}
			}
			
		} catch (ClassCastException e) {
			throw parametersMethodReturnedWrongType();
		}
	}
	
	private String getNamePattern(String forMethod) throws Exception{
		FrameworkMethod method = getParametersMethod(forMethod);
		if (method != null){
			MethodParameters parameters= method.getAnnotation(MethodParameters.class);
			return parameters.name();
		}
		return "";
	}
	
	private String getNamePattern() throws Exception{
		FrameworkMethod method = getParametersMethod();
		if (method != null){
			Parameters parameters= method.getAnnotation(Parameters.class);
			return parameters.name();
		}
		return NO_PARAMS_INFO;
	}

	private String nameFor(String namePattern, int index, Object[] parameters) {
		String finalPattern= namePattern.replaceAll("\\{index\\}",
				Integer.toString(index));
		String name= MessageFormat.format(finalPattern, parameters);
		return "[" + name + "]";
	}

	private Exception parametersMethodReturnedWrongType() throws Exception {
		String className= getTestClass().getName();
		String methodName= getParametersMethod().getName();
		String message= MessageFormat.format(
				"{0}.{1}() must return an Iterable of arrays.",
				className, methodName);
		return new Exception(message);
	}
	
	private List<FrameworkField> getAnnotatedFieldsByParameter() {
		return getTestClass().getAnnotatedFields(Parameter.class);
	}
    
	private boolean fieldsAreAnnotated() {
		return !getAnnotatedFieldsByParameter().isEmpty();
	}
}
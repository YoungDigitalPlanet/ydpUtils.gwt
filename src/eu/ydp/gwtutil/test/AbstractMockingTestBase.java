package eu.ydp.gwtutil.test;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class AbstractMockingTestBase<T extends Module> {

	protected Injector injector;
	private Class<T> testModuleClass;
	
	public AbstractMockingTestBase(Class<T> testModuleClass){
		this.testModuleClass = testModuleClass;
	}

	T createInstance(Object... args) {
		try {
			if (args.length == 2  &&  args[0].getClass().equals(Class[].class)  &&  args[1].getClass().equals(Class[].class)) {
				return testModuleClass.getConstructor(Class[].class, Class[].class).newInstance(args);
			} else if (args.length == 0){
				return testModuleClass.getConstructor().newInstance();
			} else {
				return testModuleClass.getConstructor(Class[].class).newInstance((Object)args);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>Setup test class, all injections will be done according to the Guice test
	 * module.</p>
	 */
	@Before
	public void setUp() {
		injector = Guice.createInjector(createInstance());
	}

	/**
	 * <p>Setup test class allowing for ignore some classes. For the specified
	 * ignored classes real implementations will be created.</p>
	 * 
	 * <p>If you want to test class A in you test case, you will typically want to
	 * treat class A as ignored. Other classes will be typically created as
	 * mocks (it is defined in the test Guice module).</p>
	 * 
	 * @param ignoreClasses classes to ignore (real implementations will be created)
	 */
	public final void setUp(Class<?>... ignoreClasses) {
		injector = Guice.createInjector(createInstance(ignoreClasses));
	}

	/**
	 * <p>Setup test class allowing for ignore some classes. For the specified
	 * ignored classes real implementations will be created. For the specified
	 * classes spies of real implementations will be created.</p>
	 * 
	 * <p>If you want to test class A in you test case, you will typically want to
	 * treat class A as ignored. You may specify that some other classes should
	 * be created as spies of the real implementations. Other classes will be
	 * typically created as mocks (it is defined in the test Guice module).</p>
	 * 
	 * @param ignoreClasses classes to ignore (real implementations will be created)
	 * @param classToSpy classes to create spies
	 */
	public final void setUp(Class<?>[] ignoreClasses, Class<?>[] classToSpy) {
		injector = Guice.createInjector(createInstance(ignoreClasses, classToSpy));
	}
}

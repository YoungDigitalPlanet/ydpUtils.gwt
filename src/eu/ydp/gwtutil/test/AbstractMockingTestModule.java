package eu.ydp.gwtutil.test;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * Guice Module for testing purposes that offers binding to Mockito mock objects.
 * 
 * @author Rafal Rybacki rrybacki@ydp.com.pl
 *
 */
public abstract class AbstractMockingTestModule extends AbstractTestModule {

	public AbstractMockingTestModule(){
		super();
	}
	
	public AbstractMockingTestModule(Class<?>... ignoreClassList) {
		super(ignoreClassList);
	}

	/**
	 * If ignored binds the class. If not ignored binds to mock provider.
	 * 
	 * @param clazz Class to bind.
	 */
	public <T> void bindToClassOrMockProvider(final Class<T> clazz) {
		if (isIgnoreClass(clazz)) {
			bind(clazz);
		} else {
			binder.bind(clazz).toProvider(new Provider<T>() {

				@Override
				public T get() {
					return mock(clazz);
				}
			});
		}
	}

	/**
	 * If ignored binds the class as Singleton. If not ignored binds to mock instance.
	 * 
	 * @param clazz Class to bind.
	 */
	public <T> void bindToSingletonOrMockInstance(final Class<T> clazz) {
		if (isIgnoreClass(clazz)) {
			binder.bind(clazz).in(Singleton.class);
		} else {
			binder.bind(clazz).toInstance(mock(clazz));
		}
	}

	/**
	 * If ignored binds the class as Singleton. If not ignored binds to mock instance with deep stubs.
	 * 
	 * @param clazz Class to bind.
	 */
	public <T> void bindToSingletonOrMockInstanceDeep(final Class<T> clazz) {
		if (isIgnoreClass(clazz)) {
			binder.bind(clazz).in(Singleton.class);
		} else {
			binder.bind(clazz).toInstance(mock(clazz, RETURNS_DEEP_STUBS));
		}
	}

}

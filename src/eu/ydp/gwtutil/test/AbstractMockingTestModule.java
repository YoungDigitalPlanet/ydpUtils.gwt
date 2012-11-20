package eu.ydp.gwtutil.test;

import static org.mockito.Mockito.mock;

import org.mockito.MockSettings;

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
		bindToClassOrMockProvider(clazz, null);
	}

	/**
	 * If ignored binds the class. If not ignored binds to mock provider. Allows for the specification of the answer,
	 * 
	 * @param clazz Class to bind.
	 * @param settings Mockito mock settings
	 */
	public <T> void bindToClassOrMockProvider(final Class<T> clazz, final MockSettings settings) {
		if (isIgnoreClass(clazz)) {
			bind(clazz);
		} else {
			binder.bind(clazz).toProvider(new Provider<T>() {

				@Override
				public T get() {
					return createMock(clazz, settings);
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
		bindToSingletonOrMockInstance(clazz, null);
	}

	/**
	 * If ignored binds the class as Singleton to the provided 'to' class. If not ignored binds to mock instance.
	 * 
	 * @param clazz Class to bind.
	 * @param to Class to bind to.
	 */
	public <T> void bindToSingletonOrMockInstance(final Class<T> clazz, final MockSettings settings) {
		if (isIgnoreClass(clazz)) {
			binder.bind(clazz).in(Singleton.class);			
		} else {
			binder.bind(clazz).toInstance(createMock(clazz, settings));
		}
	}

	private <T> T createMock(Class<T> clazz, MockSettings settings){
		if (settings == null){
			return mock(clazz);
		}
		return mock(clazz, settings);
	}
}

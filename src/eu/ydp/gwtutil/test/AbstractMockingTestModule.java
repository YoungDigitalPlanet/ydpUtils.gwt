package eu.ydp.gwtutil.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private final List<Class<?>> classToSpy;
	
	public AbstractMockingTestModule(){
		super();
		classToSpy = new ArrayList<Class<?>>();
	}
	
	public AbstractMockingTestModule(Class<?>... ignoreClassList) {
		super(ignoreClassList);
		classToSpy = new ArrayList<Class<?>>();
	}
	
	public AbstractMockingTestModule(Class<?>[] ignoreClassList, Class<?>[] classToSpy){
		super(ignoreClassList);
		this.classToSpy = Arrays.asList(classToSpy);
	}

	/**
	 * If ignored binds the class. If not ignored binds to mock provider.
	 * 
	 * @param clazz Class to bind.
	 */
	public <T> void bindToClassOrMockProvider(final Class<T> clazz) {
		bindToClassOrMockProvider(clazz, null, null);
	}

	/**
	 * If ignored binds the class to the specified class. If not ignored binds to mock provider.
	 * 
	 * @param clazz Class to bind.
	 */
	public <T> void bindToClassOrMockProvider(final Class<T> clazz, final Class<? extends T> to) {
		bindToClassOrMockProvider(clazz, to, null);
	}

	/**
	 * If ignored binds the class. If not ignored binds to mock provider. Allows for the specification of the answer,
	 * 
	 * @param clazz Class to bind.
	 * @param settings Mockito mock settings
	 */
	public <T> void bindToClassOrMockProvider(final Class<T> clazz, final MockSettings settings) {
		bindToClassOrMockProvider(clazz, null, settings);
	}

	/**
	 * If ignored binds the class to specified class. If not ignored binds to mock provider. Allows for the specification of the answer,
	 * 
	 * @param clazz Class to bind.
	 * @param settings Mockito mock settings
	 */
	public <T> void bindToClassOrMockProvider(final Class<T> clazz, final Class<? extends T> to, final MockSettings settings) {
		if (isIgnoreClass(clazz)) {
			if (to == null){
				binder.bind(clazz);
			} else {
				binder.bind(clazz).to(to);
			}
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
		bindToSingletonOrMockInstance(clazz, null, null);
	}

	/**
	 * If ignored binds the class as Singleton. If not ignored binds to mock instance.
	 * 
	 * @param clazz Class to bind.
	 */
	public <T> void bindToSingletonOrMockInstance(final Class<T> clazz, final Class<? extends T> to) {
		bindToSingletonOrMockInstance(clazz, to, null);
	}

	/**
	 * If ignored binds the class as Singleton. If not ignored binds to mock instance.
	 * 
	 * @param clazz Class to bind.
	 * @param to Class to bind to.
	 */
	public <T> void bindToSingletonOrMockInstance(final Class<T> clazz, final MockSettings settings) {
		bindToSingletonOrMockInstance(clazz, null, settings);
	}

	/**
	 * If ignored binds the class as Singleton to the provided 'to' class. If not ignored binds to mock instance.
	 * 
	 * @param clazz Class to bind.
	 * @param to Class to bind to.
	 */
	public <T> void bindToSingletonOrMockInstance(final Class<T> clazz, final Class<? extends T> to, final MockSettings settings) {
		if (isIgnoreClass(clazz)) {
			if (to == null){
				binder.bind(clazz).in(Singleton.class);
			} else {
				binder.bind(clazz).to(to).in(Singleton.class);
			}
		} else {
			binder.bind(clazz).toInstance(createMock(clazz, settings));
		}
	}

	private <T> T createMock(Class<T> clazz, MockSettings settings){
		if (classToSpy.contains(clazz)){
			try {
				return spy(clazz.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (settings == null){
			return mock(clazz);
		}
		return mock(clazz, settings);
	}
}

package eu.ydp.gwtutil.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;

public abstract class AbstractTestModule implements Module {

	protected Binder binder;
	private final List<Class<?>> ignoreClassList = new ArrayList<Class<?>>();

	@Override
	public void configure(Binder binder) {
		this.binder = binder;

		configure();
	}

	public AbstractTestModule() {
	}

	public abstract void configure();

	public AbstractTestModule(final Class<?>... ignoreClassList) {
		for (Class<?> ignore : ignoreClassList) {
			this.ignoreClassList.add(ignore);
		}
	}

	public <T> AnnotatedBindingBuilder<T> bind(final Class<T> clazz) {
		if (isIgnoreClass(clazz)) {
			return new NullAnnotatedBindingBuilder<T>();
		} else {
			return binder.bind(clazz);
		}
	}

	public void install (Module module){
		binder.install(module);
	}

	/**
	 * If ignored binds the class. Otherwise binding is omitted.
	 *
	 * @param clazz Class to bind.
	 */
	public <T> AnnotatedBindingBuilder<T> bindWhenIgnored(final Class<T> clazz) {
		if (isIgnoreClass(clazz)) {
			return binder.bind(clazz);
		} else {
			return new NullAnnotatedBindingBuilder<T>();
		}
	}

	protected boolean isIgnoreClass(final Class<?> clazz) {
		for (Class<?> ignoreClass : ignoreClassList) {
			if (ignoreClass == clazz) {
				return true;
			}
		}
		return false;
	}

	private static class NullAnnotatedBindingBuilder<T> implements AnnotatedBindingBuilder<T> {

		@Override
		public LinkedBindingBuilder<T> annotatedWith(final Class<? extends Annotation> arg0) {
			return null;
		}

		@Override
		public LinkedBindingBuilder<T> annotatedWith(final Annotation arg0) {
			return null;
		}

		@Override
		public ScopedBindingBuilder to(final Class<? extends T> arg0) {//NOPMD
			return null;
		}

		@Override
		public ScopedBindingBuilder to(final TypeLiteral<? extends T> arg0) {//NOPMD
			return null;
		}

		@Override
		public ScopedBindingBuilder to(final Key<? extends T> arg0) {//NOPMD
			return null;
		}

		@Override
		public void toInstance(final T arg0) {
		}

		@Override
		public ScopedBindingBuilder toProvider(final Provider<? extends T> arg0) {
			return null;
		}

		@Override
		public void asEagerSingleton() {
		}

		@Override
		public void in(final Class<? extends Annotation> arg0) {//NOPMD
		}

		@Override
		public void in(final Scope arg0) {//NOPMD
		}

		@Override
		public ScopedBindingBuilder toProvider(TypeLiteral<? extends javax.inject.Provider<? extends T>> providerType) {
			return null;
		}

		@Override
		public <S extends T> ScopedBindingBuilder toConstructor(Constructor<S> constructor) {
			return null;
		}

		@Override
		public <S extends T> ScopedBindingBuilder toConstructor(Constructor<S> constructor,
				TypeLiteral<? extends S> type) {
			return null;
		}

		@Override
		public ScopedBindingBuilder toProvider(Class<? extends javax.inject.Provider<? extends T>> providerType) {
			return null;
		}

		@Override
		public ScopedBindingBuilder toProvider(Key<? extends javax.inject.Provider<? extends T>> providerKey) {
			return null;
		}

	};

}

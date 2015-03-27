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
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.TypeListener;

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

	public AbstractTestModule(final Class<?>... ignoreClassList) {
		for (Class<?> ignore : ignoreClassList) {
			this.ignoreClassList.add(ignore);
		}
	}

	public AbstractTestModule(Iterable<Class<?>> ignoreClassList) {
		for (Class<?> ignore : ignoreClassList) {
			this.ignoreClassList.add(ignore);
		}
	}

	public abstract void configure();

	public void setIgnoreClasses(Class<?>[] ignoreClasses) {
		ignoreClassList.clear();
		for (Class<?> ignore : ignoreClasses) {
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

	public void install(Module module) {
		binder.install(module);
	}

	/**
	 * If ignored binds the class. Otherwise binding is omitted.
	 * 
	 * @param clazz
	 *            Class to bind.
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

	protected void bindListener(Matcher<? super TypeLiteral<?>> arg0, TypeListener arg1) {
		binder.bindListener(arg0, arg1);
	}

	private static class NullAnnotatedBindingBuilder<T> implements AnnotatedBindingBuilder<T> {

		@Override
		public LinkedBindingBuilder<T> annotatedWith(final Class<? extends Annotation> arg0) {
			return new NullLinkedBindingBuilder<T>();
		}

		@Override
		public LinkedBindingBuilder<T> annotatedWith(final Annotation arg0) {
			return new NullLinkedBindingBuilder<T>();
		}

		@Override
		public ScopedBindingBuilder to(final Class<? extends T> arg0) {// NOPMD
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder to(final TypeLiteral<? extends T> arg0) {// NOPMD
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder to(final Key<? extends T> arg0) {// NOPMD
			return new NullScopedBindingBuilder();
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
		public void in(final Class<? extends Annotation> arg0) {// NOPMD
		}

		@Override
		public void in(final Scope arg0) {// NOPMD
		}

		@Override
		public ScopedBindingBuilder toProvider(TypeLiteral<? extends javax.inject.Provider<? extends T>> providerType) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public <S extends T> ScopedBindingBuilder toConstructor(Constructor<S> constructor) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public <S extends T> ScopedBindingBuilder toConstructor(Constructor<S> constructor, TypeLiteral<? extends S> type) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder toProvider(Class<? extends javax.inject.Provider<? extends T>> providerType) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder toProvider(Key<? extends javax.inject.Provider<? extends T>> providerKey) {
			return new NullScopedBindingBuilder();
		}

	}

	private static class NullScopedBindingBuilder implements ScopedBindingBuilder {

		@Override
		public void asEagerSingleton() {
		}

		@Override
		public void in(Class<? extends Annotation> arg0) {
		}

		@Override
		public void in(Scope arg0) {
		}
	}

	private static class NullLinkedBindingBuilder<T> implements LinkedBindingBuilder<T> {

		@Override
		public void asEagerSingleton() {
		}

		@Override
		public void in(Class<? extends Annotation> arg0) {
		}

		@Override
		public void in(Scope arg0) {
		}

		@Override
		public ScopedBindingBuilder to(Class<? extends T> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder to(TypeLiteral<? extends T> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder to(Key<? extends T> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public <S extends T> ScopedBindingBuilder toConstructor(Constructor<S> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public <S extends T> ScopedBindingBuilder toConstructor(Constructor<S> arg0, TypeLiteral<? extends S> arg1) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public void toInstance(T arg0) {
		}

		@Override
		public ScopedBindingBuilder toProvider(Provider<? extends T> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder toProvider(Class<? extends javax.inject.Provider<? extends T>> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder toProvider(TypeLiteral<? extends javax.inject.Provider<? extends T>> arg0) {
			return new NullScopedBindingBuilder();
		}

		@Override
		public ScopedBindingBuilder toProvider(Key<? extends javax.inject.Provider<? extends T>> arg0) {
			return new NullScopedBindingBuilder();
		}
	}

}

package eu.ydp.gwtutil.test;

import java.util.Map;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import eu.ydp.gwtutil.client.gin.scopes.module.ModuleScoped;

@SuppressWarnings("rawtypes")	
public class MappedInjectorFactory {

	public static Injector createInjector(final Map<Class, Object> bindings) {
		Module module = new MappedModule(bindings);
		return Guice.createInjector(module);
	}

	private static class MappedModule implements Module{

		private final Map<Class, Object> bindings;

		public MappedModule(Map<Class, Object> bindings) {
			this.bindings = bindings;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void configure(Binder binder) {
			for (Class from : bindings.keySet()){
				binder.bind(from).toInstance(bindings.get(from));
				binder.bind(from).annotatedWith(ModuleScoped.class).toInstance(bindings.get(from));
			}			
		}
		
	}
}

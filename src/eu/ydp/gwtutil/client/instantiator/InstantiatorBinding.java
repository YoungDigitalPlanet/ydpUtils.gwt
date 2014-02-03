package eu.ydp.gwtutil.client.instantiator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface InstantiatorBinding {

	InstantiatorBindingPair[] value();

	Class<?> whenNotFound() default Null.class;

	static class Null {
	}
}

package eu.ydp.gwtutil.junit.mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.mockito.Mockito;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 * Mock all method from class that implement {@link Constants}
 *
 * @author plelakowski
 *
 */
public class GWTConstantsMock {
	/**
	 * Mockuje wszystkie medoy oznaczone annotacja {@link DefaultStringValue}
	 *
	 * @param mock
	 * @return mocka
	 */
	public static <T> T mockAllStringMethods(T mock, Class<?> baseClass) {
		Method[] methods = baseClass.getMethods();
		for (Method method : methods) {
			try {
				if (method.isAnnotationPresent(DefaultStringValue.class)) {
					DefaultStringValue ann = method.getAnnotation(DefaultStringValue.class);
					Mockito.when(method.invoke(mock, null)).thenReturn(ann.value());
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return mock;
	}

}

package eu.ydp.gwtutil.test.bean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BeanPropertyTester {

	private static final Integer MOCK_INT = 4;
	private static final Double MOCK_DOUBLE = 4.4d;
	private static final String MOCK_STRING = "MOCK_STRING";

	/**
	 * Tests whether setters and getters of the bean works properly.
	 * 
	 * In the test the value set using the setter is compared to the value retrieved using the getter afterwards.
	 * 
	 * This is not strict test as in {@link #test(Class, boolean)} or {@link #test(Class, boolean, String...)}. Tests only existing pairs of getters and
	 * setters. If there is only a setter or getter for a property it will be ignored in the test.
	 * 
	 * @param Bean
	 *            class.
	 */
	public <T> void testLenient(Class<T> beanClass) {
		test(beanClass, false);
	}

	/**
	 * Tests whether setters and getters of the bean works properly.
	 * 
	 * In the test the value set using the setter is compared to the value retrieved using the getter afterwards.
	 * 
	 * Bean is tested strictly, ie. there must be pair getter-setter for all properties
	 * 
	 * @param Bean
	 *            Bean class to test.
	 * @param ignore
	 *            Properties names' that should not be tested. Should start with lower case, ex {@code"couldStatus"} for {@code setCloudStatus} and
	 *            {@code getCloudStatus}.
	 */
	public <T> void test(Class<T> beanClass, String... ignore) {
		test(beanClass, true, ignore);
	}

	private <T> void test(Class<T> beanClass, boolean strict, String... ignore) {

		Object bean;
		try {
			bean = beanClass.newInstance();
		} catch (Exception e) {
			throw new BeanTestInitializationException(beanClass);
		}
		Map<String, Class<?>> properties = findProperties(bean);
		List<String> ignoreList = Arrays.asList(ignore);
		for (Entry<String, Class<?>> property : properties.entrySet()) {
			String propertyName = property.getKey();
			String propertyNameCanonical = propertyName.substring(0, 1).toLowerCase();
			if (propertyName.length() > 1) {
				propertyNameCanonical += propertyName.substring(1);
			}
			if (!ignoreList.contains(propertyNameCanonical)) {
				testProperty(bean, property.getKey(), property.getValue());
				if (strict) {
					testPropertyMethods(beanClass, property);
				}
			}
		}
	}

	/**
	 * Tests getters ans setters pairs existance.
	 * 
	 * @param beanClass
	 *            Class to test.
	 * @param property
	 *            Entry that matches property name to its type. The property name is in internal representation: starting from upper case character.
	 */
	private void testPropertyMethods(Class<?> beanClass, Entry<String, Class<?>> property) {
		try {
			assertThat(beanClass.getMethod("get" + property.getKey()).getReturnType().equals(property.getValue()), equalTo(true));
		} catch (Exception e) {
			throw new BeanTestPropertyException("getter for " + property.getKey());
		}
		try {
			assertThat(beanClass.getMethod("set" + property.getKey(), property.getValue()), notNullValue());
		} catch (Exception e) {
			throw new BeanTestPropertyException("setter for " + property.getKey());
		}
	}

	private Map<String, Class<?>> findProperties(Object bean) {
		Map<String, Class<?>> beanProperties = new HashMap<String, Class<?>>();
		Method[] methods = bean.getClass().getMethods();
		for (Method method : methods) {
			String name = method.getName();
			String propertyName = name.substring(3, name.length());
			if (!beanProperties.containsKey(propertyName) && !"Class".equals(propertyName)) {
				Class<?> propertyType = null;
				if (isJavaBeanSetter(method)) {
					propertyType = method.getParameterTypes()[0];
				} else if (isJavaBeanGetter(method)) {
					propertyType = method.getReturnType();
				}
				if (propertyType != null) {
					beanProperties.put(propertyName, propertyType);
				}
			}
		}
		return beanProperties;
	}

	private void testProperty(Object bean, String property, Class<?> type) {
		Object valueMock = createMock(type);
		try {
			Method setter = bean.getClass().getMethod("set" + property, type);
			setter.invoke(bean, valueMock);
			Method getter = bean.getClass().getMethod("get" + property);
			Object retrievedValue = getter.invoke(bean);
			assertThat(retrievedValue, equalTo(valueMock));
		} catch (NoSuchMethodException e) {
			// OK - carry on, getters and setters pairs are tested in testPropertyMethods method.
		} catch (Exception e) {
			throw new BeanTestPropertyException(property, e);
		}
	}

	private Object createMock(Class<?> type) {
		if (String.class.equals(type)) {
			return MOCK_STRING;
		} else if (type.isPrimitive()) {
			if (Integer.TYPE.equals(type)) {
				return MOCK_INT;
			} else if (Double.TYPE.equals(type)) {
				return MOCK_DOUBLE;
			} else {
				throw new UnsupportedOperationException("Primitive type not yet supported in the bean test utility. "
						+ "Check BeanPropertyTester.createMock method and add your type: " + type + " .");
			}
		}
		return mock(type);
	}

	private boolean isJavaBeanSetter(Method method) {
		String name = method.getName();
		return name.startsWith("set") && name.length() > 3 && Character.isUpperCase(name.charAt(3)) && method.getParameterTypes().length == 1;
	}

	private boolean isJavaBeanGetter(Method method) {
		String name = method.getName();
		return name.startsWith("get") && name.length() > 3 && Character.isUpperCase(name.charAt(3)) && method.getParameterTypes().length == 0
				&& !method.getReturnType().equals(Void.class);
	}
}
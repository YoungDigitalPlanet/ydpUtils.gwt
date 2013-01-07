package eu.ydp.gwtutil.test.bean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BeanPropertyTester {

	/**
	 * Tests whether setters and getters of the bean works properly.
	 * 
	 * In the test the value set using the setter is compared to the value retrieved using the getter afterwards.  
	 * 
	 * Tests only existing pairs of getters and setters. If there is only a setter or getter for a property it will be ignored in the test.
	 * 
	 * @param Bean class.
	 */
	public <T> void test(Class<T> beanClass){
		test(beanClass, false);
	}

	/**
	 * Tests whether setters and getters of the bean works properly.
	 * 
	 * In the test the value set using the setter is compared to the value retrieved using the getter afterwards.
	 * 
	 * @param Bean class.
	 * @param strict Determines whether bean should be tested strictly, ie. there must be pairs getter-setter for all properties.
	 */
	public <T> void test(Class<T> beanClass, boolean strict){
		Object bean;
		try {
			bean = beanClass.newInstance();
		} catch (Exception e) {
			throw new BeanTestInitializationException(beanClass);
		}
		Map<String, Class<?>> properties = findProperties(bean);
		for (String property : properties.keySet()){
			testProperty(bean, property, properties.get(property));
		}
		if (strict){
			for (Entry<String, Class<?>> property  : properties.entrySet()){
				testPropertyMethods(beanClass, property);
			}
		}
	}
	
	/**
	 * @param property Entry that matches property name to its type. 
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

	private Map<String, Class<?>> findProperties(Object bean){
		Map<String, Class<?>> beanProperties = new HashMap<String, Class<?>>();
		Method[] methods = bean.getClass().getMethods();
		for (Method method : methods){
			String name = method.getName();
			String propertyName = name.substring(3, name.length());
			if (!beanProperties.containsKey(propertyName)  &&  !"Class".equals(propertyName)){
				Class<?> propertyType = null;
				if (isJavaBeanSetter(method)){
					propertyType = method.getParameterTypes()[0];
				} else if (isJavaBeanGetter(method)){
					propertyType = method.getReturnType();
				}
				if (propertyType != null){
					beanProperties.put(propertyName, propertyType);
				}
			}
		}
		return beanProperties;
	}
	
	private void testProperty(Object bean, String property, Class<?> type){
		Object valueMock = mock(type);
		try {
			Method setter = bean.getClass().getMethod("set" + property, type);
			if (setter != null){
				setter.invoke(bean, valueMock);
				Method getter = bean.getClass().getMethod("get" + property);
				if (getter != null){
					Object retrievedValue = getter.invoke(bean);
					assertThat(retrievedValue, equalTo(valueMock));
				}
			}
		} catch (Exception e) {
			throw new BeanTestPropertyException(property, e);
		}
	}
	
	private boolean isJavaBeanSetter(Method method){
		String name = method.getName();
		return name.startsWith("set")  &&  name.length() > 3  &&  Character.isUpperCase(name.charAt(3))  &&  method.getParameterTypes().length == 1;
	}
	
	private boolean isJavaBeanGetter(Method method){
		String name = method.getName();
		return name.startsWith("get")  &&  name.length() > 3  &&  Character.isUpperCase(name.charAt(3))  &&  
				method.getParameterTypes().length == 0  &&  !method.getReturnType().equals(Void.class);
	}
}
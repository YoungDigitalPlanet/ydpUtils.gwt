package eu.ydp.gwtutil.test.bean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
	}
	
	private Map<String, Class<?>> findProperties(Object bean){
		Map<String, Class<?>> beanProperties = new HashMap<String, Class<?>>();
		Method[] methods = bean.getClass().getMethods();
		for (Method method : methods){
			String name = method.getName();
			if (isJavaBeanSetter(method)){
				beanProperties.put(name.substring(3, name.length()), method.getParameterTypes()[0]);
			}
		}
		return beanProperties;
	}
	
	private void testProperty(Object bean, String property, Class<?> type){
		Object valueMock = mock(type);
		try {
			bean.getClass().getMethod("set" + property, type).invoke(bean, valueMock);
			Method getter = bean.getClass().getMethod("get" + property);
			if (getter != null){
				Object retrievedValue = getter.invoke(bean);
				assertThat(retrievedValue, equalTo(valueMock));
			}
		} catch (Exception e) {
			throw new BeanTestPropertyException(property);
		}
	}
	
	private boolean isJavaBeanSetter(Method method){
		String name = method.getName();
		return name.startsWith("set")  &&  name.length() > 3  &&  Character.isUpperCase(name.charAt(3))  &&  method.getParameterTypes().length == 1;
	}
}
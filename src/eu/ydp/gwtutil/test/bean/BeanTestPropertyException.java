package eu.ydp.gwtutil.test.bean;

@SuppressWarnings("serial")
public class BeanTestPropertyException extends RuntimeException {

	public BeanTestPropertyException(String property) {
		super("Failed bean test for property: " + property);
	}
}

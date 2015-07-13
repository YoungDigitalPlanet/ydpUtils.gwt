package eu.ydp.gwtutil.test.bean;

@SuppressWarnings("serial")
public class BeanTestPropertyException extends RuntimeException {

    public BeanTestPropertyException(String property) {
        this(property, null);
    }

    public BeanTestPropertyException(String property, Throwable e) {
        super("Failed bean test for property: " + property, e);
    }
}

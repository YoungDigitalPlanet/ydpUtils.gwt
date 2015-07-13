package eu.ydp.gwtutil.test.bean;

@SuppressWarnings("serial")
public class BeanTestInitializationException extends RuntimeException {

    public BeanTestInitializationException(Class<?> cls) {
        super("Error initializing bean " + cls.toString());
    }
}

package eu.ydp.gwtutil.client;

public class Wrapper<T> {

    public static <T> Wrapper<T> of(Class<T> cls) {
        return new Wrapper<T>();
    }

    public static <T> Wrapper<T> of(T obj) {
        Wrapper<T> wrapper = new Wrapper<T>();
        wrapper.setInstance(obj);
        return wrapper;
    }

    /**
     * @return {@link Wrapper} that throws {@link NullPointerException} in case when {@link #getInstance()} is called before instance has been set with
     * {@link #setInstance(Object)}
     */
    public static <T> Wrapper<T> ofStrict(Class<T> cls) {
        Wrapper<T> w = new Wrapper<T>();
        w.throwingException = true;
        return w;
    }

    private T instance;
    private boolean throwingException;

    public void setInstance(T t) {
        instance = t;
    }

    public T getInstance() {
        if (instance == null && throwingException) {
            throw new NullPointerException("Instance in wrapper is null. It is expected that not null value will be set using setInstance method.");
        }
        return instance;
    }
}

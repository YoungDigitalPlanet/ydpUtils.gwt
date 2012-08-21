package eu.ydp.gwtutil.client;

public final class ClassUtil {
	
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClassUnsafe(Class<?> aClass) {
        return (Class<T>)aClass;
    }
    
}

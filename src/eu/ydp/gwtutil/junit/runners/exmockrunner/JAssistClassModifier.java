package eu.ydp.gwtutil.junit.runners.exmockrunner;

import org.junit.runners.model.InitializationError;

public class JAssistClassModifier {

	private final ClassLoader classLoader;
	private Class<?> loadedClass;
	private final ToModifyClassFinder toModifyClassFinder;

	public JAssistClassModifier(Class<?> unitTestClass) throws InitializationError {
		toModifyClassFinder = new ToModifyClassFinder(unitTestClass);
		classLoader = new JAssistClassLoader(toModifyClassFinder, ClassLoader.getSystemClassLoader());
		try {
			loadedClass = classLoader.loadClass(unitTestClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Class<?> getModifyClass() {
		return loadedClass;
	}

}

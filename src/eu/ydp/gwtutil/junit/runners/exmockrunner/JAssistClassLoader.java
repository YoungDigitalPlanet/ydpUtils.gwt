package eu.ydp.gwtutil.junit.runners.exmockrunner;

import javassist.ClassPool;
import javassist.CtClass;

class JAssistClassLoader extends ClassLoader {
	private final JAssistClassConverter classConverter;
	private final ClassLoader delegateClassLoader;
	private final ClassPool classPool = new ClassPool(true);
	private final JAssistClassLoaderPackageToOmitRecognizer packageToOmitRecognizer = new JAssistClassLoaderPackageToOmitRecognizer();

	JAssistClassLoader(ToModifyClassFinder toModifyClassFinder, ClassLoader delegateClassLoader) {
		this.delegateClassLoader = delegateClassLoader;
		classConverter = new JAssistClassConverter(toModifyClassFinder);
	}

	@Override
	public synchronized Class<?> loadClass(String className) throws ClassNotFoundException {
		if (packageToOmitRecognizer.isPackageToOmit(className)) {
			return loadClassFromDelegateClassLoader(className);
		}
		return findClass(className);
	}

	private Class<?> loadClassFromDelegateClassLoader(String name) throws ClassNotFoundException {
		return delegateClassLoader.loadClass(name);
	}

	@Override
	protected synchronized Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			CtClass clazz = classConverter.prepareClass(classPool, name);
			if (clazz == null) {
				return loadClassFromDelegateClassLoader(name);
			} else {
				byte buf[] = clazz.toBytecode();
				return defineClass(name, buf, 0, buf.length);
			}
		} catch (Exception e) {
			throw new ClassNotFoundException(e.getMessage(), e);
		}
	}

}

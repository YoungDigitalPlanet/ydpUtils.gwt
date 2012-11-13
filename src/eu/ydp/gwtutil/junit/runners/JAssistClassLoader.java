package eu.ydp.gwtutil.junit.runners;

import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;

import org.junit.runners.model.InitializationError;

public class JAssistClassLoader {

	private final ClassLoader classLoader;
	private Class<?> loadedClass;

	public class Loadeer extends URLClassLoader {
		JAssistTranslator translator;

		public Loadeer(Class<?> classToPrepare) {
			super(((URLClassLoader) getSystemClassLoader()).getURLs(), getSystemClassLoader());
			translator = new JAssistTranslator(findClassesToPrepare(classToPrepare));
		}

		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			// TODO Auto-generated method stub
			if (name.startsWith("java.")) {
				return super.loadClass(name);
			} else if (name.startsWith("javax.xml.")) {
				return super.loadClass(name);
			} else if (name.startsWith("sun.")) {
				return super.loadClass(name);
			} else if (name.startsWith("org.")) {
				return super.loadClass(name);
			} else if (name.startsWith("org.mockito.")) {
				return super.loadClass(name);
			} else if (name.startsWith("eu.ydp.gwtutil.junit.runners.")) {
				return super.loadClass(name);
			}
			return findClass(name);

		}

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			try {
				CtClass clazz = translator.prepareClass(ClassPool.getDefault(), name);
				if (clazz == null) {
					return super.loadClass(name);
				} else {
					byte buf[] = clazz.toBytecode();
					return defineClass(name, buf, 0, buf.length);
				}
			} catch (Exception e) {
				throw new ClassNotFoundException(e.getMessage(), e);
			}
		}

	}

	public JAssistClassLoader(Class<?> unitTestClass) throws InitializationError {
		classLoader = new Loadeer(unitTestClass);
		try {
			loadedClass = classLoader.loadClass(unitTestClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Class<?> getModifyClass() {
		return loadedClass;
	}

	private Set<Class<?>> findClassesToPrepare(Class<?> unitTestClass) {
		Set<Class<?>> classestoReturn = new HashSet<Class<?>>();
		classestoReturn.add(unitTestClass);
		PrepareForTest annotation = unitTestClass.getAnnotation(PrepareForTest.class);
		if (annotation != null && annotation.value() != null) {
			for (Class<?> clazz : annotation.value()) {
				if (clazz != null) {
					classestoReturn.add(clazz);
				}
			}
		}
		return classestoReturn;
	}
}

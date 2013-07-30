package eu.ydp.gwtutil.junit.runners.exmockrunner;

import java.util.Set;

import com.google.common.collect.Sets;

import eu.ydp.gwtutil.junit.runners.PrepareForTest;

class ToModifyClassFinder {

	private final Set<String> classesToPrepareNames;

	public ToModifyClassFinder(Class<?> unitTestClass) {
		classesToPrepareNames = findClassesToPrepare(unitTestClass);
	}

	boolean isModifyNeeded(String className) {
		return classesToPrepareNames.contains(className);
	}

	private Set<String> findClassesToPrepare(Class<?> unitTestClass) {
		Set<String> classestoReturn = Sets.newHashSet();
		classestoReturn.add(unitTestClass.getName());
		PrepareForTest annotation = unitTestClass.getAnnotation(PrepareForTest.class);
		if ((annotation != null) && (annotation.value() != null)) {
			for (Class<?> clazz : annotation.value()) {
				if (clazz != null) {
					classestoReturn.add(clazz.getName());
				}
			}
		}
		return classestoReturn;
	}
}

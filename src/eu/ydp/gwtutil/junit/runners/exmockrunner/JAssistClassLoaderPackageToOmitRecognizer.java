package eu.ydp.gwtutil.junit.runners.exmockrunner;

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

class JAssistClassLoaderPackageToOmitRecognizer {
	private final Set<String> packageToOmit = Sets.newHashSet();

	public JAssistClassLoaderPackageToOmitRecognizer() {
		addPackagesToOmit();
	}

	private void addPackagesToOmit() {
		packageToOmit.add("java.");
		packageToOmit.add("javax.xml.");
		packageToOmit.add("sun.");
		packageToOmit.add("com.sun.xml");
		packageToOmit.add("org.");
		packageToOmit.add("org.mockito.");
		packageToOmit.add("eu.ydp.gwtutil.junit.runners.");
	}

	public boolean isPackageToOmit(final String className) {
		return Iterables.any(packageToOmit, new Predicate<String>() {
			@Override
			public boolean apply(String packagePrefix) {
				return className.startsWith(packagePrefix);
			}
		});
	}
}

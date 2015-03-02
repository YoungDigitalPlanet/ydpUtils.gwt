package eu.ydp.gwtutil.test;

public interface Mocking {

	/**
	 * Determine which classes should be ignored when creating mock instances. For ignored class real implementation will be created.
	 * 
	 * @param ignoreClassList
	 *            Array of classes.
	 */
	void setIgnoreClasses(Class<?>[] ignoreClassList);

	/**
	 * Determine for which classes Mockito spy instances should be created. Please note that spied classes are not injected, but instantiated using reflection.
	 * 
	 * @param classesToSpy
	 *            Array of classes.
	 */
	void setSpyClasses(Class<?>[] classesToSpy);

	void mockOnlySelected(Class<?>[] classesToMock);
}

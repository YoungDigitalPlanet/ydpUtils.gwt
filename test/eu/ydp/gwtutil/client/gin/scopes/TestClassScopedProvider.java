package eu.ydp.gwtutil.client.gin.scopes;

import com.google.inject.Inject;
import com.google.inject.Provider;

class TestClassScopedProvider extends CustomScopedProvider<TestClass> {

	/**
	 * 
	 */
	private final GinScopesTest TestClassScopedProvider;

	@Inject
	public TestClassScopedProvider(GinScopesTest ginScopesTest, Provider<TestClass> provider, CurrentScopeProvider currentScopeProvider) {
		super(provider, currentScopeProvider);
		TestClassScopedProvider = ginScopesTest;
	}

}
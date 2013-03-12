package gin.scopes;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ydp.gwtutil.gin.scopes.CurrentScopeProvider;
import eu.ydp.gwtutil.gin.scopes.CustomScopedProvider;

class TestClassScopedProvider extends CustomScopedProvider<TestClass>{

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
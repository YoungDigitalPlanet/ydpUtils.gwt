package eu.ydp.gwtutil.client.gin.scopes;

import eu.ydp.gwtutil.client.gin.scopes.AbstractCustomScope;
import eu.ydp.gwtutil.client.gin.scopes.CurrentScopeProvider;

class TestCurrentScopeProvider implements CurrentScopeProvider{

	private TestCustomScope testCustomScope = new TestCustomScope();
	
	class TestCustomScope extends AbstractCustomScope{

		@Override
		public boolean equals(Object object) {
			return super.defaultReferenceEquals(object);
		}

		@Override
		public int hashCode() {
			return super.defaultReferenceHashCode();
		}
		
	}
	
	@Override
	public AbstractCustomScope getCurrentScope() {
		return testCustomScope;
	}

	
	
}

package eu.ydp.gwtutil.client.gin.scopes;

public abstract class AbstractCustomScope {

	@Override
	public abstract boolean equals(Object object);
	
	@Override
	public abstract int hashCode();
	
	public boolean defaultReferenceEquals(Object object){
		return super.equals(object);
	}
	
	public int defaultReferenceHashCode(){
		return super.hashCode();
	}
}

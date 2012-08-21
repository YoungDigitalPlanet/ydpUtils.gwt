package eu.ydp.gwtutil.client;

public class Pair<T,U> {
	private T one;
	private U two;
	
	public Pair( T o1, U o2 ) {
		one = o1;
		two = o2;
	}
	public T getOne() {
		return one;
	}
	public U getTwo() {
		return two;
	}
	public int hashCode() {
		return 13*(one==null ? 0 : one.hashCode())+(two==null ? 0 : two.hashCode());
	}
	@SuppressWarnings("unchecked")
	public boolean equals( Object o ) {
		if ( o instanceof Pair ) {
			Pair<T,U> p2 = (Pair<T,U>)o;
			return objectEqual(one, p2.one) && objectEqual(two, p2.two);
		} else
			return false;
	}
	
	public static boolean objectEqual( Object o1, Object o2 ) {
		return o1==o2 || (o1!=null && o2!=null && o1.equals(o2));
	}
}

package eu.ydp.gwtutil.client.constants;

/**
 * Allow for dynamic creation of string constants using GWT generators.
 * 
 * Sample implementation:
 * 
 * <pre>
 * {@code
 * @Prefix("pref")
 * @Suffix("suf")
 * @Separator("-")  // defines characters separating components
 * public interface MyConstants extends StringConstants {
 * 		MyConstants first();
 * 		MyConstants second();
 * }
 * 
 * // ...
 * 
 * String s1 = myConstants.first().toString(); // will return "pref-first-suf"
 * String s1 = myConstants.firstSecond().toString(); // will return "pref-first-second-suf"
 * String s2 = myConstants.first().second().toString(); // will return "pref-first-second-suf"
 * 
 * }
 * </pre>
 *
 * @author rrybacki
 * 
 * @see Prefix
 * @see Suffix
 * @see Separator
 * @see Output
 */
public interface StringConstants {

	/**
	 * Call this method as the exit from string constant builder chain.
	 * 
	 * Ex. <code>String output =  stringConstantsImpl.aaa().bbb().toString();</code>
	 * 
	 * @return Built String object containing desired string constant
	 */
	@Override
	String toString();
}

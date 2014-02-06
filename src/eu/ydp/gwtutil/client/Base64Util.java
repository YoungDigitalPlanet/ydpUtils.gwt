package eu.ydp.gwtutil.client;

public class Base64Util {

	/**
	 * Encodes string to base 64.
	 */
	public String encode(String str) {
		return encodeJs(str);
	}

	private native String encodeJs(String str) /*-{
												return $wnd.btoa($wnd.unescape($wnd.encodeURIComponent( str )));
												}-*/;

	/**
	 * Decodes string from base 64.
	 */
	public String decode(String str) {
		return decodeJs(str);
	}

	private native String decodeJs(String str) /*-{
												// remove padding
												var unpadded = str.replace(/(%3D)+$/gi,'');
												// decode
												return $wnd.decodeURIComponent($wnd.escape($wnd.atob( unpadded )));
												}-*/;
}

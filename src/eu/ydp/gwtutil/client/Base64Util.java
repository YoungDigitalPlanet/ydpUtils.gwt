package eu.ydp.gwtutil.client;

public class Base64Util {

	public String stringToBase64(String str){
		return stringToBase64Js(str);
	}
	
	private native String stringToBase64Js(String str) /*-{
	    return $wnd.btoa($wnd.unescape($wnd.encodeURIComponent( str )));
	}-*/;
}

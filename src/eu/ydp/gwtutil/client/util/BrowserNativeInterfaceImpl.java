package eu.ydp.gwtutil.client.util;

public class BrowserNativeInterfaceImpl implements BrowserNativeInterface {

	@Override
	public native boolean isUserAgent(String regex, String userAgent)/*-{
		var reg = eval("/" + regex + "/");
		return reg.test(userAgent);
	}-*/;

	@Override
	public native String getUserAgentStrting()/*-{
		return navigator.userAgent;
	}-*/;

	@Override
	public native boolean isLocal()/*-{
		return (window.location.href.indexOf("file://") == 0) ? true : false;
	}-*/;

}

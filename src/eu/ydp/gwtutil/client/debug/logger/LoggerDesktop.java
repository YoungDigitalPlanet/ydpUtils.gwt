package eu.ydp.gwtutil.client.debug.logger;


public class LoggerDesktop implements Logger {
	
	public native void log(String text) /*-{
		console.log(text);
	}-*/;

}

package eu.ydp.gwtutil.client.debug.logger;


public class LoggerJSConsole implements Logger {
	
	@Override
	public void log(String text) {
		logToJSConsole(text);
	}
	
	private native void logToJSConsole(String text) /*-{
		console.log(text);
	}-*/;
}

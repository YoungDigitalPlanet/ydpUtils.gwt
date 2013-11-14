package eu.ydp.gwtutil.client.debug.logger;

public class LoggerJS implements Logger {

	@Override
	public void log(String text) {
		logToJS(text);
	}

	private native void logToJS(String msg)/*-{
		if (typeof $wnd.logFromEmpiria == 'function') {
			$wnd.logFromEmpiria(msg);
		}
	}-*/;
}

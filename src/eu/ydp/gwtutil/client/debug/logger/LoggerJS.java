package eu.ydp.gwtutil.client.debug.logger;

/**
 * 
 * @deprecated use {@link eu.ydp.gwtutil.client.debug.log.Logger} instead
 */
@Deprecated
public class LoggerJS implements Logger {

	@Override
	@Deprecated
	public void log(String text) {
		logToJS(text);
	}

	private native void logToJS(String msg)/*-{
		if (typeof $wnd.logFromEmpiria == 'function') {
			$wnd.logFromEmpiria(msg);
		}
	}-*/;
}

package eu.ydp.gwtutil.client.debug.logger;

/**
 * @deprecated use {@link eu.ydp.gwtutil.client.debug.log.Logger} instead
 */
@Deprecated
public class LoggerJSConsole implements Logger {

    @Override
    @Deprecated
    public void log(String text) {
        logToJSConsole(text);
    }

    private native void logToJSConsole(String text) /*-{
        console.log(text);
    }-*/;
}

package eu.ydp.gwtutil.client.debug.gwtlogger;

/**
 * @deprecated use {@link eu.ydp.gwtutil.client.debug.log.Logger} instead
 */
@Deprecated
public interface ILogger {

    @Deprecated
    void fine(String message);

    @Deprecated
    void info(String message);

    @Deprecated
    void severe(String message);

    @Deprecated
    void warning(String message);
}

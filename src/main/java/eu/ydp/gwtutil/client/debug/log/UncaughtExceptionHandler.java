package eu.ydp.gwtutil.client.debug.log;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

public class UncaughtExceptionHandler implements GWT.UncaughtExceptionHandler {

    private final Logger logger;

    @Inject
    public UncaughtExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onUncaughtException(Throwable e) {
        logger.error(e);
    }
}

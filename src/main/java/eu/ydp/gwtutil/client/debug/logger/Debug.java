package eu.ydp.gwtutil.client.debug.logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @deprecated use {@link eu.ydp.gwtutil.client.debug.log.Logger} instead
 */
@Deprecated
public final class Debug {
	private static Logger logger = null; // NOPMD

	private Debug() {
	}

	@Deprecated
	public static void log(Object text) {
		getLogger().log(String.valueOf(text.toString()));
	}

	@Deprecated
	public static void log(Class<?> source, Object text) {
		log(source.getName().replaceAll("^.*[.]", "") + " : " + text);
	}

	@Deprecated
	public static boolean isDebug() {
		return !(getLogger() instanceof LoggerEmpty);
	}

	private static Logger getLogger() {
		if (logger == null) {
			// debug = GWT.create(Logger.class);
			logger = GWT.create(LoggerDOM.class);

			if (logger instanceof IsWidget) {
				RootPanel.get().add((Widget) logger);
			}
		}
		return logger;
	}
}

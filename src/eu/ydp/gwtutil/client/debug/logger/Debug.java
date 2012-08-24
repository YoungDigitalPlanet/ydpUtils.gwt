package eu.ydp.gwtutil.client.debug.logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public final class Debug {
	private static Logger debug = null;  // NOPMD

	private Debug(){}
	
	public static void log(Object text) {
		getLogger().log(String.valueOf(text.toString()));
	}

	public static void log(Class<?> source,Object text){
		log(source.getName().replaceAll("^.*[.]", "") +" : "+text);
	}

	public static boolean isDebug(){
		return !(getLogger() instanceof LoggerEmpty);
	}

	private static Logger getLogger(){
		if (debug == null)
		{
			debug = GWT.create(Logger.class);

			if (debug instanceof IsWidget) {
				RootPanel.get().add((Widget) debug);
			}
		}
		return debug;
	}
}

package eu.ydp.gwtutil.client.debug.logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Debug {
	private static Logger _debug = null;

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
		if (_debug == null)
		{
			_debug = GWT.create(Logger.class);

			if (_debug instanceof IsWidget) {
				RootPanel.get().add((Widget) _debug);
			}
		}
		return _debug;
	}
}

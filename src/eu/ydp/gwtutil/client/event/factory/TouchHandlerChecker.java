package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.shared.EventHandler;

public class TouchHandlerChecker {

	public <T extends EventHandler> boolean isOnlyOneFinger(TouchEvent<T> touchEvent) {
		JsArray<Touch> touches = touchEvent.getTouches();
		return isOnlyOneFinger(touches);
	}

	public boolean isOnlyOneFinger(JsArray<Touch> touches) {
		return touches.length() == 1;
	}
}

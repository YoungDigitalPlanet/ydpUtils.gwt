package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.shared.EventHandler;

public class TouchEventChecker {

	public <T extends EventHandler> boolean isOnlyOneFinger(TouchEvent<T> touchEvent) {
		JsArray<Touch> touches = touchEvent.getTouches();
		return isOnlyOneFinger(touches);
	}

	public boolean isOnlyOneFinger(JsArray<Touch> touches) {
		return touches != null && touches.length() == 1;
	}

	public <T extends EventHandler> boolean noFingerTouch(TouchEvent<T> touchEvent) {
		JsArray<Touch> touches = touchEvent.getTouches();
		return touches != null && touches.length() == 0;
	}
}

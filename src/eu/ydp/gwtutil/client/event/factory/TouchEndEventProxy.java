package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.ui.Widget;

class TouchEndEventProxy implements EventHandlerProxy {
	private final Command command;
	private final TouchHandlerChecker touchHandlerChecker = new TouchHandlerChecker();

	public TouchEndEventProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {
		widget.addDomHandler(new TouchEndHandler() {

			@Override
			public void onTouchEnd(TouchEndEvent event) {
				if (touchHandlerChecker.isOnlyOneFinger(event)) {
					command.execute(event.getNativeEvent());
				}
			}
		}, TouchEndEvent.getType());
	}
}
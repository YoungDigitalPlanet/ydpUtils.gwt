package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.ui.Widget;

class TouchStartEventProxy implements EventHandlerProxy {
	private final Command command;
	private final TouchEventChecker touchEventChecker = new TouchEventChecker();

	public TouchStartEventProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {
		widget.addDomHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				if (touchEventChecker.isOnlyOneFinger(event)) {
					command.execute(event.getNativeEvent());
				}
			}
		}, TouchStartEvent.getType());
	}
}
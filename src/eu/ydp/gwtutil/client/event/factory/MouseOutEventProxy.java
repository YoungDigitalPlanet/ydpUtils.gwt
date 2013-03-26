package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.Widget;

class MouseOutEventProxy implements EventHandlerProxy {
	private final Command command;

	public MouseOutEventProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {
		widget.addDomHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				command.execute(event.getNativeEvent());
			}
		}, MouseOutEvent.getType());
	}
}
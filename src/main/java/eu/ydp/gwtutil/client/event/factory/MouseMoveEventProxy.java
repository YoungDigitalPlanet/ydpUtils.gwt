package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.Widget;

public class MouseMoveEventProxy implements EventHandlerProxy {

	private final Command command;

	public MouseMoveEventProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {

		widget.addDomHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				command.execute(event.getNativeEvent());
			}
		}, MouseMoveEvent.getType());
	}
}

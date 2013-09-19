package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.Widget;

public class MouseDownEventProxy implements EventHandlerProxy {

	private final Command command;

	public MouseDownEventProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {
		widget.addDomHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				command.execute(event.getNativeEvent());
			}

		}, MouseDownEvent.getType());
	}
}

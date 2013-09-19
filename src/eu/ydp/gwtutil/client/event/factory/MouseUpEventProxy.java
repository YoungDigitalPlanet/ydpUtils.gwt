package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Widget;

public class MouseUpEventProxy implements EventHandlerProxy {

	private final Command command;

	public MouseUpEventProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {
		widget.addDomHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				command.execute(event.getNativeEvent());
			}
		}, MouseUpEvent.getType());
	}

}

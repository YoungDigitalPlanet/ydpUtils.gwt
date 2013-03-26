package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.ui.Widget;

public class ClickByTouchProxy implements EventHandlerProxy, TouchStartHandler, TouchEndHandler{

	private final Command command;
	private boolean touchStart;
	public ClickByTouchProxy(Command command) {
		this.command = command;
	}

	@Override
	public void apply(Widget widget) {
		widget.addDomHandler(this, TouchStartEvent.getType());
		widget.addDomHandler(this, TouchEndEvent.getType());
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		if(touchStart){
			command.execute(event.getNativeEvent());
			touchStart = false;
		}
	}

	@Override
	public void onTouchStart(TouchStartEvent event) {
		touchStart = true;
	}

}

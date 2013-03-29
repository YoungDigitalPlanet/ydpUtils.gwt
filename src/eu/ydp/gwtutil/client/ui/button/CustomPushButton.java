package eu.ydp.gwtutil.client.ui.button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.PushButton;

public class CustomPushButton extends PushButton {

	private final CustomPushButtonEventHandler clickEventHandler = new CustomPushButtonEventHandler(this);

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return clickEventHandler.addHandler(handler, ClickEvent.getType());
	}

}

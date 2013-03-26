package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchEvent;

import eu.ydp.gwtutil.client.util.UserAgentChecker;

public class UserInteractionHandlerFactory {
	public EventHandlerProxy createUserClickHandler(Command command) {
		if (isTouchSupported()) {
			return new ClickByTouchProxy(command);
		} else {
			return new ClickEventProxy(command);
		}
	}

	public EventHandlerProxy createUserOverHandler(Command command) {
		if (isTouchSupported()) {
			return new TouchStartEventProxy(command);
		} else {
			return new MouseOverEventProxy(command);
		}
	}

	public EventHandlerProxy createUserOutHandler(Command command) {
		if (isTouchSupported()) {
			return new TouchEndEventProxy(command);
		} else {
			return new MouseOutEventProxy(command);
		}
	}

	public EventHandlerProxy createUserTouchStartHandler(Command command) {
		if (isTouchSupported()) {
			return new TouchStartEventProxy(command);
		} else {
			return new NoEventProxy();
		}
	}

	public EventHandlerProxy createUserTouchEndHandler(Command command) {
		if (isTouchSupported()) {
			return new TouchEndEventProxy(command);
		} else {
			return new NoEventProxy();
		}
	}

	private boolean isTouchSupported() {
		return UserAgentChecker.isMobileUserAgent() && TouchEvent.isSupported();
	}
}

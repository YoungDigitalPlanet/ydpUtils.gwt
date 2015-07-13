package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.user.client.ui.IsWidget;
import eu.ydp.gwtutil.client.util.UserAgentChecker;

public class UserInteractionHandlerFactory {

    public void applyUserClickHandler(Command command, IsWidget widget) {
        EventHandlerProxy proxy;
        if (isTouchSupported()) {
            proxy = new ClickByTouchProxy(command);
        } else {
            proxy = new ClickEventProxy(command);
        }
        proxy.apply(widget.asWidget());
    }

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

    public EventHandlerProxy createUserMoveHandler(Command command) {
        if (isTouchSupported()) {
            return new TouchMoveEventProxy(command);
        } else {
            return new MouseMoveEventProxy(command);
        }
    }

    public EventHandlerProxy createUserUpHandler(Command command) {
        if (isTouchSupported()) {
            return new TouchEndEventProxy(command);
        } else {
            return new MouseUpEventProxy(command);
        }
    }

    public EventHandlerProxy createUserDownHandler(Command command) {
        if (isTouchSupported()) {
            return new TouchDownEventProxy(command);
        } else {
            return new MouseDownEventProxy(command);
        }
    }

    private boolean isTouchSupported() {
        return UserAgentChecker.isMobileUserAgent() && TouchEvent.isSupported();
    }
}

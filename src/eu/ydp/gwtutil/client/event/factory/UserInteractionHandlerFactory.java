package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchEvent;

import eu.ydp.gwtutil.client.util.UserAgentChecker;

public class UserInteractionHandlerFactory {
	public EventHandlerProxy createUserClickHandler(Command command){
		if(isTouchSupported()){
			return new TouchEndEventProxy(command);
		}else{
			return new ClickEventProxy(command);
		}
	}

	public EventHandlerProxy createUserOverHandler(Command command){
		if(isTouchSupported()){
			return new TouchStartEventProxy(command);
		}else{
			return new MouseOverEventProxy(command);
		}
	}
	private boolean isTouchSupported() {
		return UserAgentChecker.isMobileUserAgent() && TouchEvent.isSupported();
	}
}

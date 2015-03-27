package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.dom.client.NativeEvent;

public interface Command {
	public void execute(NativeEvent event);
}

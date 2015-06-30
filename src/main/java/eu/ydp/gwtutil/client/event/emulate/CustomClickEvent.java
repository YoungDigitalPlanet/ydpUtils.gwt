package eu.ydp.gwtutil.client.event.emulate;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;

public class CustomClickEvent extends ClickEvent {
    public CustomClickEvent(NativeEvent event) {
        setNativeEvent(event);
    }

}

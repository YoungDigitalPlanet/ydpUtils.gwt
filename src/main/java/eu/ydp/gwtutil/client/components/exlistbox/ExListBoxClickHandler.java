package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.dom.client.NativeEvent;

public interface ExListBoxClickHandler {
	void onClick(NativeEvent event, ExListBoxOption selectedOption);
}

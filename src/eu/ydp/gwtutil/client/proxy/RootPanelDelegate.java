package eu.ydp.gwtutil.client.proxy;

import com.google.gwt.user.client.ui.RootPanel;

public class RootPanelDelegate {
	public int getOffsetWidth() {
		return RootPanel.get().getOffsetWidth();
	}
}

package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.PopupPanel;

public class PopupPanelAutoHideEnablerTimer extends Timer {

	private final PopupPanel popupPanel;

	public PopupPanelAutoHideEnablerTimer(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
	}

	@Override
	public void run() {
		popupPanel.setAutoHideEnabled(true);
	}

}

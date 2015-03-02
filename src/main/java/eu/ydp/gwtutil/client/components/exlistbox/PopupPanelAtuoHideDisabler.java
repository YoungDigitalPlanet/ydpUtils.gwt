package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.PopupPanel;

public class PopupPanelAtuoHideDisabler implements CloseHandler<PopupPanel> {

	@Override
	public void onClose(CloseEvent<PopupPanel> event) {
		event.getTarget().setAutoHideEnabled(false);
	}
}

package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.PopupPanel;

public interface ExListBoxOpenCloseListener extends CloseHandler<PopupPanel> {
    void onOpen();
}

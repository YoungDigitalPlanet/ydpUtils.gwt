package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.user.client.ui.IsWidget;

import eu.ydp.gwtutil.client.components.exlistbox.ExListBox.PopupPosition;

public interface IsExListBox extends IsWidget {

	void addOption(IsWidget baseBody, IsWidget popupBody);

	void addOption(ExListBoxOption option);

	void setChangeListener(ExListBoxChangeListener listener);

	int getSelectedIndex();

	void setSelectedIndex(int index);

	void setEnabled(boolean enabled);

	boolean isEnabled();

	void setPopupPosition(PopupPosition pp);

	public void hidePopup();

	PopupPosition getPopupPosition();

	void addOpenCloseListener(ExListBoxOpenCloseListener openCloseListener);
}

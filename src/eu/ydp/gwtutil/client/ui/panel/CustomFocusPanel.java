package eu.ydp.gwtutil.client.ui.panel;

import com.google.gwt.user.client.ui.FocusPanel;

import eu.ydp.gwtutil.client.ui.InputToButtonReplacer;

public class CustomFocusPanel extends FocusPanel {
	
	public CustomFocusPanel() {
		super();
		InputToButtonReplacer.changeInputTypeToButton(this);
	}

}

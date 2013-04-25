package eu.ydp.gwtutil.client.ui.button;

import com.google.gwt.user.client.ui.ToggleButton;

import eu.ydp.gwtutil.client.ui.InputToButtonReplacer;

public class CustomToggleButton extends ToggleButton {
	
	public CustomToggleButton() {
		super();
		InputToButtonReplacer replacer = new InputToButtonReplacer();
		replacer.changeInputTypeToButton(this);
	}

}

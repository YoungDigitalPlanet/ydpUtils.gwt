package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ExListBoxOption {

	private final Widget baseBody;
	private final ExListBoxOptionWidget optionWidget;

	public ExListBoxOption(IsWidget baseBody, IsWidget popupBody){
		this.baseBody = baseBody.asWidget();
		optionWidget = new ExListBoxOptionWidget(popupBody);
	}

	public Widget getPopupBody(){
		return optionWidget;
	}

	public Widget getBaseBody(){
		return baseBody;
	}

	public void setSelected(boolean sel) {
		optionWidget.setSelected(sel);
	}

	public void reset(){
		optionWidget.reset();
	}

	public void setLocked(boolean locked){
		optionWidget.setLocked(locked);
	}

}

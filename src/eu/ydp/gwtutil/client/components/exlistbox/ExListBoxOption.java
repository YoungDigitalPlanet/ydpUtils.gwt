package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ExListBoxOption {
	
	private Widget baseBody;
	private ExListBoxOptionWidget optionWidget;
	
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
}

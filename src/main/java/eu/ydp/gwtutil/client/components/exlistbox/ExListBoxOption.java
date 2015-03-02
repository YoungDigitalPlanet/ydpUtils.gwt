package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ExListBoxOption {

	private final Widget baseBody;
	private final ExListBoxOptionWidget optionWidget;

	public ExListBoxOption(IsWidget baseBody, IsWidget popupBody) {
		this.baseBody = baseBody.asWidget();
		optionWidget = new ExListBoxOptionWidget(popupBody, this);
	}

	public Widget getPopupBody() {
		return optionWidget;
	}

	public Widget getBaseBody() {
		return baseBody;
	}

	public void setSelected(boolean sel) {
		optionWidget.setSelected(sel);
	}

	public void reset() {
		optionWidget.reset();
	}

	public void setClickHandler(ExListBoxClickHandler handler) {
		optionWidget.setClickHandler(handler);
	}

	public void setOverHandler(ExListBoxOverHandler handler) {
		optionWidget.setOverHandler(handler);
	}

	public void setOver(boolean over) {
		optionWidget.setOver(over);
	}

}

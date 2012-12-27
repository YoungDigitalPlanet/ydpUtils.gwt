package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ExListBoxPopup extends Composite implements ContainsWidgetOptions {

	private static ExListBoxPopupContentsUiBinder uiBinder = GWT.create(ExListBoxPopupContentsUiBinder.class);

	interface ExListBoxPopupContentsUiBinder extends UiBinder<Widget, ExListBoxPopup> {}

	public ExListBoxPopup() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField Panel popupContents;

	@Override
	public void addOption(Widget option) {
		popupContents.add(option);
	}

}

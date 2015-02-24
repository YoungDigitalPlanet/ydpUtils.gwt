package eu.ydp.gwtutil.client.debug.log;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class DOMAppenderWidget extends Composite {

	private static DOMAppenderWidgetUiBinder uiBinder = GWT.create(DOMAppenderWidgetUiBinder.class);

	interface DOMAppenderWidgetUiBinder extends UiBinder<Widget, DOMAppenderWidget> {
	}

	@UiField
	TextArea textArea;

	public DOMAppenderWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	public String getText() {
		return textArea.getText();
	}
}

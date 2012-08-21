package eu.ydp.gwtutil.client.debug.logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class LoggerMobile extends Composite implements Logger {

	private static DebugUiBinder uiBinder = GWT.create(DebugUiBinder.class);

	@UiField
	protected TextArea consoleText;

	interface DebugUiBinder extends UiBinder<Widget, LoggerMobile> {
	}

	public LoggerMobile() {
		initWidget(uiBinder.createAndBindUi(this));
		//this.setText("Console log");
	}

	@Override
	protected void onAttach() {
		super.onAttach();
	}

	@Override
	public void log(String text) {
		StringBuilder builder = new StringBuilder(text);
		builder.append("\n");
		builder.append(consoleText.getText());
		consoleText.setText( builder.toString());
	}

	@UiHandler("clearButton")
	void clearHandler(ClickEvent event) {
		consoleText.setText("");
	}

	@UiHandler("closeButton")
	void closeHandler(ClickEvent event) {
		this.setVisible(false);
	}

}


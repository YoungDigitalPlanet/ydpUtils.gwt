package eu.ydp.gwtutil.client.debug.log;

import com.google.gwt.user.client.ui.RootPanel;

public class DOMAppender implements LogAppender {

	private static boolean isInitialized = false;
	private static DOMAppenderWidget widget;

	public DOMAppender() {
		createPlaceHolder();
	}

	@Override
	public void appendeMessage(String message) {
		String text = widget.getText();
		widget.setText(message + "\n" + text);
	}

	private void createPlaceHolder() {
		if (!isInitialized) {
			widget = new DOMAppenderWidget();
			RootPanel.get().add(widget);
			isInitialized = true;
		}
	}
}

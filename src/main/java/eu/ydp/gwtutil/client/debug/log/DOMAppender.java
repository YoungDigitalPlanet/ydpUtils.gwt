package eu.ydp.gwtutil.client.debug.log;

import com.google.gwt.user.client.ui.RootPanel;

public class DOMAppender implements LogAppender {

    private static DOMAppenderWidget widget = null;

    public DOMAppender() {
        createPlaceHolder();
    }

    @Override
    public void appendMessage(String message) {
        String text = widget.getText();
        widget.setText(message + "\n" + text);
    }

    private void createPlaceHolder() {
        if (widget == null) {
            widget = new DOMAppenderWidget();
            RootPanel.get().add(widget);
        }
    }
}

package eu.ydp.gwtutil.client.proxy;

import com.google.gwt.user.client.Window;

public class WindowDelegate {
	public int getScrollTop() {
		return Window.getScrollTop();
	}

	public int getClientWidth() {
		return Window.getClientWidth();
	}

	public void scrollTo(int left, int top) {
		Window.scrollTo(left, top);
	}
}

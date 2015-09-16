package eu.ydp.gwtutil.client.proxy;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

public class WindowDelegate {
    public int getScrollTop() {
        return Window.getScrollTop();
    }

    public int getClientWidth() {
        return Window.getClientWidth();
    }

    public int getClientHeight() {
        return Window.getClientHeight();
    }

    public void scrollTo(int left, int top) {
        Window.scrollTo(left, top);
    }

    public void addResizeHandler(ResizeHandler handler) {
        Window.addResizeHandler(handler);
    }

    public int getInnerWidth() {
        return getInnerWidthNative();
    }

    private native int getInnerWidthNative()/*-{
        return $wnd.innerWidth;
    }-*/;
}

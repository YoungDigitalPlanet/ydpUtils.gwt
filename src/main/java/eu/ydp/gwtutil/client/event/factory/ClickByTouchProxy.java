package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.Widget;
import eu.ydp.gwtutil.client.debug.log.ConsoleAppender;
import eu.ydp.gwtutil.client.debug.log.Logger;
import eu.ydp.gwtutil.client.event.TouchEventReader;

public class ClickByTouchProxy implements EventHandlerProxy, TouchStartHandler, TouchEndHandler, ClickHandler {

    public static final int PIXELS_MOVE_TOLERANTION = 7;

    private final TouchEventReader touchEventReader;
    private final Command command;

    private boolean touchStart;
    private Point touchStartPoint;

    public ClickByTouchProxy(Command command) {
        this.command = command;
        this.touchEventReader = new TouchEventReader();
    }

    public ClickByTouchProxy(Command command, TouchEventReader touchEventReader) {
        this.command = command;
        this.touchEventReader = touchEventReader;
    }

    @Override
    public void apply(Widget widget) {
        widget.addDomHandler(this, TouchStartEvent.getType());
        widget.addDomHandler(this, TouchEndEvent.getType());
        widget.addDomHandler(this, ClickEvent.getType());
    }

    @Override
    public void onTouchStart(TouchStartEvent event) {
        this.touchStartPoint = pointFromTouchStartEvent(event.getNativeEvent());
        touchStart = true;
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {

        if (touchStart) {
            NativeEvent nativeEvent = event.getNativeEvent();
            event.preventDefault();

            Point touchEndPoint = pointFromTouchEndEvent(nativeEvent);
            if (wasNotSwipe(touchEndPoint)) {
                command.execute(nativeEvent);
            }

            touchStart = false;
            touchStartPoint = null;
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if (!touchStart) {
            command.execute(event.getNativeEvent());
        }
    }

    private boolean wasNotSwipe(Point touchEndPoint) {
        double xMove = Math.abs(touchStartPoint.getX() - touchEndPoint.getX());
        double yMove = Math.abs(touchStartPoint.getY() - touchEndPoint.getY());
        boolean wasNotSwipe = (xMove < PIXELS_MOVE_TOLERANTION) && (yMove < PIXELS_MOVE_TOLERANTION);
        return wasNotSwipe;
    }

    private Point pointFromTouchEndEvent(NativeEvent nativeEvent) {
        int screenX = touchEventReader.getFromChangedTouchesX(nativeEvent);
        int screenY = touchEventReader.getFromChangedTouchesScreenY(nativeEvent);
        return new Point(screenX, screenY);
    }

    private Point pointFromTouchStartEvent(NativeEvent nativeEvent) {
        int screenX = touchEventReader.getX(nativeEvent);
        int screenY = touchEventReader.getScreenY(nativeEvent);
        return new Point(screenX, screenY);
    }
}

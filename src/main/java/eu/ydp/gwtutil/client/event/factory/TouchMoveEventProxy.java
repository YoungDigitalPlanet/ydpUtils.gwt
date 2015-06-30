package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.user.client.ui.Widget;

public class TouchMoveEventProxy implements EventHandlerProxy {

    private final Command command;
    private final TouchEventChecker touchEventChecker = new TouchEventChecker();

    public TouchMoveEventProxy(Command command) {
        this.command = command;
    }

    @Override
    public void apply(Widget widget) {
        widget.addDomHandler(new TouchMoveHandler() {

            @Override
            public void onTouchMove(TouchMoveEvent event) {
                if (touchEventChecker.isOnlyOneFinger(event)) {
                    command.execute(event.getNativeEvent());
                }
            }
        }, TouchMoveEvent.getType());
    }

}

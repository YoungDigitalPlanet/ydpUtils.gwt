package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Widget;

class MouseOverEventProxy implements EventHandlerProxy {
    private final Command command;

    public MouseOverEventProxy(Command command) {
        this.command = command;
    }

    @Override
    public void apply(Widget widget) {
        widget.addDomHandler(new MouseOverHandler() {

            @Override
            public void onMouseOver(MouseOverEvent event) {
                command.execute(event.getNativeEvent());
            }
        }, MouseOverEvent.getType());
    }
}

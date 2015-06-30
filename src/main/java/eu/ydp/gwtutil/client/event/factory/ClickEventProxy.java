package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

class ClickEventProxy implements EventHandlerProxy {

    private final Command command;

    public ClickEventProxy(Command command) {
        this.command = command;
    }

    @Override
    public void apply(Widget widget) {
        widget.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                command.execute(event.getNativeEvent());
            }
        }, ClickEvent.getType());
    }

}

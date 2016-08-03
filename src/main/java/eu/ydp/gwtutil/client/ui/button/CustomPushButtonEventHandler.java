package eu.ydp.gwtutil.client.ui.button;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.DomEvent.Type;
import eu.ydp.gwtutil.client.event.EventHandlerRegistrator;
import eu.ydp.gwtutil.client.event.HandlerRegistration;
import eu.ydp.gwtutil.client.event.emulate.CustomClickEvent;
import eu.ydp.gwtutil.client.event.factory.Command;
import eu.ydp.gwtutil.client.event.factory.EventHandlerProxy;
import eu.ydp.gwtutil.client.event.factory.UserInteractionHandlerFactory;

import java.util.Set;

public class CustomPushButtonEventHandler extends EventHandlerRegistrator<ClickHandler, DomEvent.Type<?>> {

    private final UserInteractionHandlerFactory interactionHandlerFactory;

    private boolean isUserInteractionHandlerAdded;

    private final CustomPushButton pushButton;

    public CustomPushButtonEventHandler(CustomPushButton pushButton) {
        this.pushButton = pushButton;
        this.interactionHandlerFactory = new UserInteractionHandlerFactory();
    }

    public CustomPushButtonEventHandler(CustomPushButton pushButton, UserInteractionHandlerFactory interactionHandlerFactory) {
        this.pushButton = pushButton;
        this.interactionHandlerFactory = interactionHandlerFactory;
    }

    public HandlerRegistration addClickHandler(ClickHandler handler) {
        setUserInteractionHandler();
        return addHandler(handler, ClickEvent.getType());
    }

    private <EH extends ClickHandler> HandlerRegistration addHandler(EH handler, Type<EH> key) {
        return super.addHandler(handler, key);
    }

    private void setUserInteractionHandler() {
        if (!isUserInteractionHandlerAdded) {
            isUserInteractionHandlerAdded = true;
            EventHandlerProxy userClickHandler = createUserClickHandler();
            userClickHandler.apply(pushButton);
        }
    }

    private EventHandlerProxy createUserClickHandler() {
        EventHandlerProxy userClickHandler = interactionHandlerFactory.createUserClickHandler(new Command() {
            @Override
            public void execute(NativeEvent event) {
                event.preventDefault();
                removeFocus();
                fireEvent(new CustomClickEvent(event));
            }
        });
        return userClickHandler;
    }

    private void fireEvent(ClickEvent event) {
        final Set<ClickHandler> eventHandlers = getHandlersAccordingToRunningMode(event.getAssociatedType());
        for (ClickHandler handler : eventHandlers) {
            handler.onClick(event);
        }
    }

    private void removeFocus() {
        pushButton.getElement().focus();
        pushButton.getElement().blur();
    }
}
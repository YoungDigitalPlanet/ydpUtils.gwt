package eu.ydp.gwtutil.client.ui.button;

import com.google.common.collect.Sets;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.Element;
import com.google.gwtmockito.GwtMockitoTestRunner;
import eu.ydp.gwtutil.client.event.HandlerRegistration;
import eu.ydp.gwtutil.client.event.emulate.CustomClickEvent;
import eu.ydp.gwtutil.client.event.factory.Command;
import eu.ydp.gwtutil.client.event.factory.UserInteractionHandlerFactory;
import eu.ydp.gwtutil.client.util.UserAgentChecker;
import eu.ydp.gwtutil.junit.mock.UserAgentCheckerNativeInterfaceMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class CustomPushButtonEventHandlerJUnitTest {

    private final UserInteractionHandlerFactory userInteractionHandlerFactory = spy(new UserInteractionHandlerFactory());
    private CustomPushButtonEventHandler instance;
    private final ArgumentCaptor<ClickHandler> clickEventCaptor = ArgumentCaptor.forClass(ClickHandler.class);
    private Element buttonElement;

    @Before
    public void before() {
        buttonElement = mock(Element.class);
        CustomPushButton pushButton =  mock(CustomPushButton.class);
        when(pushButton.getElement()).thenReturn(buttonElement);
        doReturn(null).when(pushButton)
                .addDomHandler(clickEventCaptor.capture(), Matchers.any(DomEvent.Type.class));
        instance = new CustomPushButtonEventHandler(pushButton, userInteractionHandlerFactory);
        UserAgentChecker.setNativeInterface(UserAgentCheckerNativeInterfaceMock.getNativeInterfaceMock(UserAgentCheckerNativeInterfaceMock.FIREFOX_WINDOWS));

    }

    @Test
    public void fireTest() {
        ClickHandler clickHandler = mock(ClickHandler.class);
        instance.addClickHandler(clickHandler);
        NativeEvent nativeEvent = mock(NativeEvent.class);
        CustomClickEvent click = new CustomClickEvent(nativeEvent);
        clickEventCaptor.getValue()
                .onClick(click);

        ArgumentCaptor<ClickEvent> eventCaptor = ArgumentCaptor.forClass(ClickEvent.class);
        verify(clickHandler).onClick(eventCaptor.capture());
        verify(nativeEvent).preventDefault();
        verify(buttonElement).focus();
        assertEquals(click.getNativeEvent(), eventCaptor.getValue()
                .getNativeEvent());
    }

    @Test
    public void multipleAddSameHandlerTest() {
        ClickHandler clickHandler = mock(ClickHandler.class);
        instance.addClickHandler(clickHandler);
        instance.addClickHandler(clickHandler);

        NativeEvent nativeEvent = mock(NativeEvent.class);
        CustomClickEvent click = new CustomClickEvent(nativeEvent);
        clickEventCaptor.getValue()
                .onClick(click);

        ArgumentCaptor<ClickEvent> eventCaptor = ArgumentCaptor.forClass(ClickEvent.class);
        verify(clickHandler).onClick(eventCaptor.capture());
        assertEquals(click.getNativeEvent(), eventCaptor.getValue()
                .getNativeEvent());
    }

    private Set<ClickHandler> getclickHandlers() {
        Set<ClickHandler> handlers = Sets.newHashSet();
        for (int x = 0; x < 5; ++x) {
            handlers.add(mock(ClickHandler.class));
        }
        return handlers;
    }

    @Test
    public void fireTestMultipleHandlers() {
        Set<ClickHandler> clickHandlers = getclickHandlers();
        for (ClickHandler handler : clickHandlers) {
            instance.addClickHandler(handler);
        }
        NativeEvent nativeEvent = mock(NativeEvent.class);
        CustomClickEvent click = new CustomClickEvent(nativeEvent);
        clickEventCaptor.getValue()
                .onClick(click);

        for (ClickHandler handler : clickHandlers) {
            ArgumentCaptor<ClickEvent> eventCaptor = ArgumentCaptor.forClass(ClickEvent.class);
            verify(handler).onClick(eventCaptor.capture());
            assertEquals(click.getNativeEvent(), eventCaptor.getValue()
                    .getNativeEvent());
        }
    }

    @Test
    public void removeHandlerTest() {
        ClickHandler clickHandler = mock(ClickHandler.class);
        HandlerRegistration addHandler = instance.addClickHandler(clickHandler);
        addHandler.removeHandler();
        CustomClickEvent click = new CustomClickEvent(mock(NativeEvent.class));
        clickEventCaptor.getValue()
                .onClick(click);

        verify(clickHandler, times(0)).onClick(Matchers.any(ClickEvent.class));
    }

    @Test
    public void addUserClickHandlerTest() {
        ClickHandler handler = mock(ClickHandler.class);
        instance.addClickHandler(handler);
        instance.addClickHandler(handler);

        verify(userInteractionHandlerFactory).createUserClickHandler(Matchers.any(Command.class));
    }

    @Test
    public void removeUserClickHandlerTest() {
        ClickHandler handler = mock(ClickHandler.class);
        HandlerRegistration handlerRegistration = instance.addClickHandler(handler);
        handlerRegistration.removeHandler();

        ClickHandler userInteractFactoryclickHandler = clickEventCaptor.getValue();
        NativeEvent nativeEvent = mock(NativeEvent.class);
        userInteractFactoryclickHandler.onClick(new CustomClickEvent(nativeEvent));

        verify(handler, times(0)).onClick(Matchers.any(ClickEvent.class));

    }

    @Test
    public void fireClickHandlerTest() {
        ClickHandler handler = mock(ClickHandler.class);
        instance.addClickHandler(handler);

        ClickHandler userInteractFactoryclickHandler = clickEventCaptor.getValue();
        NativeEvent nativeEvent = mock(NativeEvent.class);
        userInteractFactoryclickHandler.onClick(new CustomClickEvent(nativeEvent));

        verify(handler).onClick(Matchers.any(ClickEvent.class));
        verify(userInteractionHandlerFactory).createUserClickHandler(Matchers.any(Command.class));
    }

}

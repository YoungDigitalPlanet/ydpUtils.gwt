package eu.ydp.gwtutil.client.ui.button;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.google.common.collect.Sets;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;

import eu.ydp.gwtutil.client.event.HandlerRegistration;
import eu.ydp.gwtutil.client.event.emulate.CustomClickEvent;
import eu.ydp.gwtutil.client.event.factory.Command;
import eu.ydp.gwtutil.client.event.factory.UserInteractionHandlerFactory;
import eu.ydp.gwtutil.client.util.UserAgentChecker;
import eu.ydp.gwtutil.junit.mock.UserAgentCheckerNativeInterfaceMock;
import eu.ydp.gwtutil.junit.runners.ExMockRunner;
import eu.ydp.gwtutil.junit.runners.PrepareForTest;

@RunWith(ExMockRunner.class)
@PrepareForTest({Widget.class,NativeEvent.class})
public class CustomPushButtonEventHandlerJUnitTest {

	private final UserInteractionHandlerFactory userInteractionHandlerFactory = spy(new UserInteractionHandlerFactory());
	private CustomPushButtonEventHandler instance;
	private final ArgumentCaptor<ClickHandler> clickEventCaptor = ArgumentCaptor.forClass(ClickHandler.class);

	@Before
	public void before() {
		CustomPushButton pushButton = mock(CustomPushButton.class);
		doReturn(null).when(pushButton).addDomHandler(clickEventCaptor.capture(), Mockito.any(DomEvent.Type.class));
		instance = new CustomPushButtonEventHandler(pushButton,userInteractionHandlerFactory);
		UserAgentChecker.setNativeInterface(UserAgentCheckerNativeInterfaceMock.getNativeInterfaceMock(UserAgentCheckerNativeInterfaceMock.FIREFOX_WINDOWS));

	}
	@BeforeClass
	public static void disarm() {
		GWTMockUtilities.disarm();
	}

	@AfterClass
	public static void rearm() {
		GWTMockUtilities.restore();
	}

	@Test
	public void fireTest() {
		ClickHandler clickHandler = mock(ClickHandler.class);
		instance.addClickHandler(clickHandler);
		NativeEvent nativeEvent = mock(NativeEvent.class);
		CustomClickEvent click = new CustomClickEvent(nativeEvent);
		clickEventCaptor.getValue().onClick(click);

		ArgumentCaptor<ClickEvent> eventCaptor = ArgumentCaptor.forClass(ClickEvent.class);
		verify(clickHandler).onClick(eventCaptor.capture());
		verify(nativeEvent).preventDefault();
		assertEquals(click.getNativeEvent(), eventCaptor.getValue().getNativeEvent());
	}

	@Test
	public void multipleAddSameHandlerTest() {
		ClickHandler clickHandler = mock(ClickHandler.class);
		instance.addClickHandler(clickHandler);
		instance.addClickHandler(clickHandler);

		NativeEvent nativeEvent = mock(NativeEvent.class);
		CustomClickEvent click = new CustomClickEvent(nativeEvent);
		clickEventCaptor.getValue().onClick(click);

		ArgumentCaptor<ClickEvent> eventCaptor = ArgumentCaptor.forClass(ClickEvent.class);
		verify(clickHandler).onClick(eventCaptor.capture());
		assertEquals(click.getNativeEvent(), eventCaptor.getValue().getNativeEvent());
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
		clickEventCaptor.getValue().onClick(click);

		for (ClickHandler handler : clickHandlers) {
			ArgumentCaptor<ClickEvent> eventCaptor = ArgumentCaptor.forClass(ClickEvent.class);
			verify(handler).onClick(eventCaptor.capture());
			assertEquals(click.getNativeEvent(), eventCaptor.getValue().getNativeEvent());
		}
	}

	@Test
	public void removeHandlerTest() {
		ClickHandler clickHandler = mock(ClickHandler.class);
		HandlerRegistration addHandler = instance.addClickHandler(clickHandler);
		addHandler.removeHandler();
		CustomClickEvent click = new CustomClickEvent(mock(NativeEvent.class));
		clickEventCaptor.getValue().onClick(click);

		verify(clickHandler, times(0)).onClick(Mockito.any(ClickEvent.class));
	}

	@Test
	public void addUserClickHandlerTest(){
		ClickHandler handler = mock(ClickHandler.class);
		instance.addClickHandler(handler);
		instance.addClickHandler(handler);

		verify(userInteractionHandlerFactory).createUserClickHandler(Mockito.any(Command.class));
	}

	@Test
	public void removeUserClickHandlerTest(){
		ClickHandler handler = mock(ClickHandler.class);
		HandlerRegistration handlerRegistration = instance.addClickHandler(handler);
		handlerRegistration.removeHandler();

		ClickHandler userInteractFactoryclickHandler = clickEventCaptor.getValue();
		NativeEvent nativeEvent = mock(NativeEvent.class);
		userInteractFactoryclickHandler.onClick(new CustomClickEvent(nativeEvent));

		verify(handler,times(0)).onClick(Mockito.any(ClickEvent.class));

	}

	@Test
	public void fireClickHandlerTest(){
		ClickHandler handler = mock(ClickHandler.class);
		instance.addClickHandler(handler);

		ClickHandler userInteractFactoryclickHandler = clickEventCaptor.getValue();
		NativeEvent nativeEvent = mock(NativeEvent.class);
		userInteractFactoryclickHandler.onClick(new CustomClickEvent(nativeEvent));

		verify(handler).onClick(Mockito.any(ClickEvent.class));
		verify(userInteractionHandlerFactory).createUserClickHandler(Mockito.any(Command.class));
	}

}

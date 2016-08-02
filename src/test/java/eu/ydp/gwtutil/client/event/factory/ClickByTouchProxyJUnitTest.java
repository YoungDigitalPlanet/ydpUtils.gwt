package eu.ydp.gwtutil.client.event.factory;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import eu.ydp.gwtutil.client.event.TouchEventReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class ClickByTouchProxyJUnitTest {

    private ClickByTouchProxy clickByTouchProxy;
    private Command command;
    private TouchEventReader touchEventReader;
    private TouchStartEvent touchStartEvent;
    private TouchEndEvent touchEndEvent;

    @Before
    public void setUp() throws Exception {
        command = mock(Command.class);
        touchEventReader = mock(TouchEventReader.class);
        clickByTouchProxy = new ClickByTouchProxy(command, touchEventReader);
    }

    @Test
    public void shouldIgnoreClickBecauseOfVerticalMove() throws Exception {
        prepareTouchStartEventWithCoords(0, 0);
        clickByTouchProxy.onTouchStart(touchStartEvent);

        prepareTouchEndEventWithCoords(ClickByTouchProxy.PIXELS_MOVE_TOLERANTION + 1, 0);
        clickByTouchProxy.onTouchEnd(touchEndEvent);

        Mockito.verifyZeroInteractions(command);
    }

    @Test
    public void shouldIgnoreClickBecauseOfHorizontalMove() throws Exception {
        prepareTouchStartEventWithCoords(0, 0);
        clickByTouchProxy.onTouchStart(touchStartEvent);

        prepareTouchEndEventWithCoords(0, ClickByTouchProxy.PIXELS_MOVE_TOLERANTION + 1);
        clickByTouchProxy.onTouchEnd(touchEndEvent);

        Mockito.verifyZeroInteractions(command);
    }

    @Test
    public void shouldAcceptClickBecauseMoveIsInTolerance() throws Exception {
        prepareTouchStartEventWithCoords(0, 0);
        clickByTouchProxy.onTouchStart(touchStartEvent);

        NativeEvent touchEndNativeEvent = prepareTouchEndEventWithCoords(ClickByTouchProxy.PIXELS_MOVE_TOLERANTION - 1,
                ClickByTouchProxy.PIXELS_MOVE_TOLERANTION - 1);
        clickByTouchProxy.onTouchEnd(touchEndEvent);

        verify(command).execute(touchEndNativeEvent);
    }

    @Test
    public void shouldExecuteClickHandler_whenClickEventWasFired_andTouchWasNotStarted() throws Exception {
        // GIVEN
        prepareTouchStartEventWithCoords(0, 0);

        NativeEvent clickNativeEvent = mock(NativeEvent.class);

        ClickEvent givenClickEvent = mock(ClickEvent.class);
        when(givenClickEvent.getNativeEvent()).thenReturn(clickNativeEvent);

        // WHEN
        clickByTouchProxy.onClick(givenClickEvent);

        // THEN
        verify(command).execute(clickNativeEvent);
    }

    @Test
    public void shouldNotExecuteCommand_whenTouchWasStarted() throws Exception {
        // GIVEN
        prepareTouchStartEventWithCoords(0, 0);
        clickByTouchProxy.onTouchStart(touchStartEvent);

        NativeEvent clickNativeEvent = mock(NativeEvent.class);

        ClickEvent givenClickEvent = mock(ClickEvent.class);
        when(givenClickEvent.getNativeEvent()).thenReturn(clickNativeEvent);

        // WHEN
        clickByTouchProxy.onClick(givenClickEvent);

        // THEN
        verify(command, never()).execute(clickNativeEvent);
    }

    private NativeEvent prepareTouchEndEventWithCoords(int xCoordinate, int yCoordinate) {
        touchEndEvent = mock(TouchEndEvent.class);
        NativeEvent nativeEvent = mock(NativeEvent.class);
        when(touchEndEvent.getNativeEvent()).thenReturn(nativeEvent);

        when(touchEventReader.getFromChangedTouchesX(nativeEvent)).thenReturn(xCoordinate);

        when(touchEventReader.getFromChangedTouchesScreenY(nativeEvent)).thenReturn(yCoordinate);

        return nativeEvent;
    }

    private void prepareTouchStartEventWithCoords(int xCoordinate, int yCoordinate) {
        touchStartEvent = mock(TouchStartEvent.class);
        NativeEvent nativeEvent = mock(NativeEvent.class);
        when(touchStartEvent.getNativeEvent()).thenReturn(nativeEvent);

        when(touchEventReader.getX(nativeEvent)).thenReturn(xCoordinate);

        when(touchEventReader.getScreenY(nativeEvent)).thenReturn(yCoordinate);
    }
}

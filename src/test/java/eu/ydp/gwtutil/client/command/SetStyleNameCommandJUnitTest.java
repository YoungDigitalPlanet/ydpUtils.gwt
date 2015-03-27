package eu.ydp.gwtutil.client.command;

import static org.mockito.Mockito.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class SetStyleNameCommandJUnitTest {

	private SetStyleNameCommand instance;
	private final IsWidget isWidget = mock(IsWidget.class);
	private Widget widget;
	private final String styleName = "styleName";

	@BeforeClass
	public static void disarm() {
		GWTMockUtilities.disarm();
	}

	@AfterClass
	public static void rearm() {
		GWTMockUtilities.restore();
	}

	@Before
	public void before() {
		widget = mock(Widget.class);
		doReturn(widget).when(isWidget).asWidget();
		instance = new SetStyleNameCommand(isWidget, styleName);
	}

	@Test
	public void testExecute() {
		instance.execute(null);
		verify(widget).setStyleName(Matchers.eq(styleName));
		Mockito.verifyNoMoreInteractions(widget);
	}

}

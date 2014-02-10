package eu.ydp.gwtutil.client.util.geom;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.Widget;

import eu.ydp.gwtutil.junit.runners.ExMockRunner;
import eu.ydp.gwtutil.junit.runners.PrepareForTest;

@RunWith(ExMockRunner.class)
@PrepareForTest(Widget.class)
public class WidgetSizeTest {

	private Widget widget;

	@Before
	public void setUp() throws Exception {
		widget = Mockito.mock(Widget.class);
		when(widget.asWidget()).thenReturn(widget);
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
	public void shouldPropelySetSizeOnWidget() throws Exception {
		// given
		int width = 100;
		int height = 222;
		Size size = new Size(width, height);
		WidgetSize widgetSize = new WidgetSize(size);

		// when
		widgetSize.setOnWidget(widget);

		// then
		String expectedWidth = width + WidgetSize.DIMENSIONS_UNIT;
		String expectedHeight = height + WidgetSize.DIMENSIONS_UNIT;

		verify(widget).setWidth(expectedWidth);
		verify(widget).setHeight(expectedHeight);
	}
}

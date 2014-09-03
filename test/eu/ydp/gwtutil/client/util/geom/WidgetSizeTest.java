package eu.ydp.gwtutil.client.util.geom;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class WidgetSizeTest {

	private Widget widget;

	@Before
	public void setUp() throws Exception {
		widget = Mockito.mock(Widget.class);
		when(widget.asWidget()).thenReturn(widget);
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

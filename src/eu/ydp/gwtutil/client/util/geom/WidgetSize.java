package eu.ydp.gwtutil.client.util.geom;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class WidgetSize {

	public static final String DIMENSIONS_UNIT = "px";
	private final int width;
	private final int height;
	
	public WidgetSize(Size size) {
		this(size.getWidth(), size.getHeight());
	}
	
	public WidgetSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setOnWidget(IsWidget widget) {
		Widget asWidget = widget.asWidget();
		asWidget.setWidth(convertToPx(width));
		asWidget.setHeight(convertToPx(height));
	}
	
	private String convertToPx(int value) {
		return (value + DIMENSIONS_UNIT);
	}
}

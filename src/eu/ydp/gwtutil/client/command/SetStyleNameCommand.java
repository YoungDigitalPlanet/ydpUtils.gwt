package eu.ydp.gwtutil.client.command;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.ui.IsWidget;

import eu.ydp.gwtutil.client.event.factory.Command;

public class SetStyleNameCommand implements Command {
	private final String styleName;
	private final IsWidget widget;

	public SetStyleNameCommand(IsWidget widget,String styleName) {
		this.widget = widget;
		this.styleName = styleName;

	}

	@Override
	public void execute(NativeEvent event) {
		widget.asWidget().setStyleName(styleName);
	}

}

package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import eu.ydp.gwtutil.client.event.factory.Command;
import eu.ydp.gwtutil.client.event.factory.EventHandlerProxy;
import eu.ydp.gwtutil.client.event.factory.UserInteractionHandlerFactory;

public class ExListBoxOptionWidget extends Composite {

	private static ExListBoxOptionPopupWidgetUiBinder uiBinder = GWT.create(ExListBoxOptionPopupWidgetUiBinder.class);

	interface ExListBoxOptionPopupWidgetUiBinder extends UiBinder<Widget, ExListBoxOptionWidget> {
	}

	private class SetOverCommand implements Command {
		private final boolean over;

		public SetOverCommand(boolean over) {
			this.over = over;
		}

		@Override
		public void execute(NativeEvent event) {
			setOver(over);
		}
	}

	@UiField
	FocusPanel popupPanel;
	@UiField
	FlowPanel popupContainer;
	@UiField
	FocusPanel popupButton;

	private boolean selected = false;
	private boolean over = false;
	private boolean locked;
	private final UserInteractionHandlerFactory userInteractionHandlerFactory = new UserInteractionHandlerFactory();

	public ExListBoxOptionWidget(IsWidget popupBody) {
		initWidget(uiBinder.createAndBindUi(this));
		addOverHandler();
		addClickHandler();
		insertPopupBody(popupBody);
	}

	private void insertPopupBody(IsWidget popupBody) {
		popupContainer.insert(popupBody, 0);
	}

	private void addOverHandler() {
		EventHandlerProxy userOverHandler = userInteractionHandlerFactory.createUserOverHandler(new SetOverCommand(true));
		userOverHandler.apply(popupPanel);
	}

	private void addClickHandler() {
		EventHandlerProxy userClickHandler = userInteractionHandlerFactory.createUserClickHandler(new SetOverCommand(false));
		userClickHandler.apply(popupPanel);
	}

	@UiHandler("popupPanel")
	public void onMouseOut(MouseOutEvent event) {
		setOver(false);
	}

	protected void setOver(boolean over) {
		this.over = over;
		updateStyle();
	}

	public void setSelected(boolean sel) {
		this.selected = sel;
		updateStyle();
	}

	public void reset() {
		over = false;
		selected = false;
		locked = false;
		updateStyle();
	}

	private void updateStyle() {
		if (!locked) {
			ExListBoxStyleNames popupPanelStyleName;
			if (selected && over) {
				popupPanelStyleName = ExListBoxStyleNames.INSTANCE.popupOptionPanelSelectedOver();
			} else if (selected) {
				popupPanelStyleName = ExListBoxStyleNames.INSTANCE.popupOptionPanelSelected();
			} else if (over) {
				popupPanelStyleName = ExListBoxStyleNames.INSTANCE.popupOptionPanelOver();
			} else {
				popupPanelStyleName = ExListBoxStyleNames.INSTANCE.popupOptionPanel();
			}

			popupPanel.setStyleName(popupPanelStyleName.toString());

			if (selected) {
				popupButton.addStyleName(ExListBoxStyleNames.INSTANCE.popupOptionButtonSelected().toString());
			} else {
				popupButton.removeStyleName(ExListBoxStyleNames.INSTANCE.popupOptionButtonSelected().toString());
			}
		}
	}

	public void setLocked(boolean locked) {
		this.locked = locked;

	}
}

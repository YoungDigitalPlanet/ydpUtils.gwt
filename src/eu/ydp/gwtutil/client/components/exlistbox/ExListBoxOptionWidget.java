package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
			overHandler.onEvent(over, exListBoxOption);
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
	private final UserInteractionHandlerFactory userInteractionHandlerFactory = new UserInteractionHandlerFactory();
	private ExListBoxClickHandler clickHandler;
	private ExListBoxOverHandler overHandler;
	private final ExListBoxOption exListBoxOption;
	private boolean touchMove = false;

	public ExListBoxOptionWidget(IsWidget popupBody, ExListBoxOption exListBoxOption) {
		initWidget(uiBinder.createAndBindUi(this));
		this.exListBoxOption = exListBoxOption;
		addOverHandler();
		addClickHandler();
		addOutHandler();
		insertPopupBody(popupBody);
		addTouchMoveCheckHandlers();
	}

	private void addTouchMoveCheckHandlers() {
		popupPanel.addTouchMoveHandler(new TouchMoveHandler() {
			@Override
			public void onTouchMove(TouchMoveEvent event) {
				touchMove = true;
			}
		});

		popupPanel.addTouchStartHandler(new TouchStartHandler() {
			@Override
			public void onTouchStart(TouchStartEvent event) {
				touchMove = false;
			}
		});
	}

	private void insertPopupBody(IsWidget popupBody) {
		popupContainer.insert(popupBody, 0);
	}

	private void addOverHandler() {
		EventHandlerProxy userOverHandler = userInteractionHandlerFactory.createUserOverHandler(new SetOverCommand(true));
		userOverHandler.apply(popupPanel);
	}

	private void addOutHandler() {
		EventHandlerProxy userOutHandler = userInteractionHandlerFactory.createUserOutHandler(new SetOverCommand(false));
		userOutHandler.apply(popupPanel);
	}

	private void addClickHandler() {
		EventHandlerProxy userClickHandler = userInteractionHandlerFactory.createUserClickHandler(createUserClickHandlerCommand());
		userClickHandler.apply(popupPanel);
	}

	private Command createUserClickHandlerCommand() {
		return new Command() {
			@Override
			public void execute(NativeEvent event) {
				if (!touchMove) {
					event.preventDefault();
					clickHandler.onClick(event, exListBoxOption);
				}
			}
		};
	}

	public void setOver(boolean over) {
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
		updateStyle();
	}

	private void updateStyle() {
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

	public void setClickHandler(ExListBoxClickHandler handler) {
		this.clickHandler = handler;
	}

	public void setOverHandler(ExListBoxOverHandler handler) {
		this.overHandler = handler;
	}

}

package eu.ydp.gwtutil.client.components.exlistbox;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import eu.ydp.gwtutil.client.event.factory.Command;
import eu.ydp.gwtutil.client.event.factory.EventHandlerProxy;
import eu.ydp.gwtutil.client.event.factory.UserInteractionHandlerFactory;

/**
 * Custom combo box widget that display list of options on the popup.
 *
 * Use {@link #addOption(IsWidget, IsWidget)} to add options.
 *
 * @author rrybacki@ydp.com.pl
 */
// TODO commit
public class ExListBox extends Composite implements IsExListBox {

	private static ExListBoxUiBinder uiBinder = GWT.create(ExListBoxUiBinder.class);

	interface ExListBoxUiBinder extends UiBinder<Widget, ExListBox> {
	}

	public static enum PopupPosition {
		ABOVE, BELOW
	};

	@UiField
	FlowPanel mainContainer;
	@UiField
	FocusPanel baseContainer;
	@UiField
	FlowPanel baseContainerInner;
	@UiField
	FlowPanel baseContents;

	private final ExListBoxPopup popupContents;
	private final PopupPanel popupPanel;
	private final List<ExListBoxOption> options;

	private int selectedIndex = 0;
	private boolean enabled = true;
	private PopupPosition popupPosition = PopupPosition.ABOVE;
	private final UserInteractionHandlerFactory userInteractionHandlerFactory = new UserInteractionHandlerFactory();

	protected ExListBoxChangeListener listener;

	public ExListBox() {
		initWidget(uiBinder.createAndBindUi(this));

		options = new ArrayList<ExListBoxOption>();

		popupContents = new ExListBoxPopup();

		popupPanel = new PopupPanel(true);
		popupPanel.setStyleName(ExListBoxStyleNames.INSTANCE.popupContainer().toString());
		popupPanel.add(popupContents);
		addShowListBoxHandler(baseContainer);
	}

	private void addShowListBoxHandler(Panel baseContainer) {
		Command showListBox = createShowListBoxCommand();
		EventHandlerProxy showListboxClickHandler = userInteractionHandlerFactory.createUserClickHandler(showListBox);
		showListboxClickHandler.apply(baseContainer);
	}

	private Command createShowListBoxCommand() {
		Command showListBox = new Command() {
			@Override
			public void execute(NativeEvent event) {
				if (enabled) {
					updateOptionButtonsSelection();
					popupPanel.show();
					updatePosition();
				}
			}
		};
		return showListBox;
	}

	@Override
	public void addOption(IsWidget baseBody, IsWidget popupBody) {
		addOption(new ExListBoxOption(baseBody, popupBody));
	}

	@Override
	public void addOption(final ExListBoxOption option) {
		options.add(option);
		popupContents.addOption(option.getPopupBody());
		Command selectedOption = createOptionIsSelectedCommand(option);
		EventHandlerProxy selectedOptionHandler = userInteractionHandlerFactory.createUserClickHandler(selectedOption);
		selectedOptionHandler.apply(option.getPopupBody());
	}

	private Command createOptionIsSelectedCommand(final ExListBoxOption option) {
		Command optionSelected = new Command() {
			@Override
			public void execute(NativeEvent event) {
				event.stopPropagation();
				int currentOptionIndex = options.indexOf(option);
				if (selectedIndex != currentOptionIndex) {
					selectedIndex = currentOptionIndex;
					setSelectedBaseBody();
					listener.onChange();
				}
				hidePopup();
			}
		};
		return optionSelected;
	}

	@Override
	public void setChangeListener(ExListBoxChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * @return Index of the currently selected option or <code>-1</code> if no
	 *         option is selected.
	 */
	@Override
	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * @param index
	 *            Index of the option to select or <code>-1</code>.
	 */
	@Override
	public void setSelectedIndex(int index) {
		if (index >= -1 && index < options.size()) {
			selectedIndex = index;
			setSelectedBaseBody();
			hidePopup();
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (this.enabled) {
			removeStyleName(ExListBoxStyleNames.INSTANCE.disabled().toString());
		} else {
			addStyleName(ExListBoxStyleNames.INSTANCE.disabled().toString());
		}
	}

	public void setShowEmptyOptions(boolean showEmptyOption) {
		this.setSelectedIndex(((showEmptyOption) ? 0 : -1));
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void setPopupPosition(PopupPosition pp) {
		popupPosition = pp;
	}

	@Override
	public PopupPosition getPopupPosition() {
		return popupPosition;
	}

	private void setSelectedBaseBody() {
		baseContents.clear();
		if (selectedIndex >= 0 && selectedIndex < options.size()) {
			baseContents.add(options.get(selectedIndex).getBaseBody());
		}
	}

	@Override
	public void hidePopup() {
		popupPanel.hide();
	}

	private void updatePosition() {
		int mountingPointX = 0;
		int mountingPointY = 0;
		final int MARGIN = 8;

		mountingPointX = baseContainer.getAbsoluteLeft() + baseContainer.getOffsetWidth() / 2 - popupPanel.getOffsetWidth() / 2;
		if (popupPosition == PopupPosition.ABOVE) {
			mountingPointY = baseContainer.getAbsoluteTop()+ baseContainer.getOffsetHeight() - popupPanel.getOffsetHeight();
		} else {
			mountingPointY = baseContainer.getAbsoluteTop() - baseContainer.getOffsetHeight() + baseContainer.getOffsetHeight();
		}

		if (mountingPointX < Window.getScrollLeft() + MARGIN) {
			mountingPointX = Window.getScrollLeft() + MARGIN;
		} else if (mountingPointX + popupPanel.getOffsetWidth() > Window.getClientWidth() + Window.getScrollLeft() + MARGIN) {
			mountingPointX = Window.getClientWidth() + Window.getScrollLeft() + MARGIN - popupPanel.getOffsetWidth();
		}

		if (mountingPointY < Window.getScrollTop() + MARGIN) {
			mountingPointY = Window.getScrollTop() + MARGIN;
		} else if (mountingPointY + popupPanel.getOffsetHeight() > Window.getClientHeight() + Window.getScrollTop() + MARGIN) {
			mountingPointY = Window.getClientHeight() + Window.getScrollTop() + MARGIN - popupPanel.getOffsetHeight();
		}

		popupPanel.setPopupPosition(mountingPointX, mountingPointY);

	}

	private void updateOptionButtonsSelection() {
		for (int i = 0; i < options.size(); i++) {
			options.get(i).setSelected(i == selectedIndex);
		}
	}

}

package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import eu.ydp.gwtutil.client.event.factory.Command;
import eu.ydp.gwtutil.client.event.factory.EventHandlerProxy;
import eu.ydp.gwtutil.client.event.factory.UserInteractionHandlerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExListBox extends Composite implements IsExListBox {

    private static ExListBoxUiBinder uiBinder = GWT.create(ExListBoxUiBinder.class);

    interface ExListBoxUiBinder extends UiBinder<Widget, ExListBox> {
    }

    public static enum PopupPosition {
        ABOVE, BELOW
    }

    ;

    private final ExListBoxDelays delays;

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
    private ExListBoxOpenCloseListener openCloseListener;

    private int selectedIndex = 0;
    private boolean enabled = true;
    private PopupPosition popupPosition = PopupPosition.ABOVE;

    private final UserInteractionHandlerFactory userInteractionHandlerFactory = new UserInteractionHandlerFactory();

    protected ExListBoxChangeListener listener;

    private final ExListBoxSelectionController selectionController = new ExListBoxSelectionController(this);

    @Inject
    public ExListBox(ExListBoxDelays delays) {
        initWidget(uiBinder.createAndBindUi(this));
        options = new ArrayList<ExListBoxOption>();
        popupContents = new ExListBoxPopup();
        popupPanel = new PopupPanel(false);
        popupPanel.addCloseHandler(new PopupPanelAtuoHideDisabler());
        popupPanel.setStyleName(ExListBoxStyleNames.INSTANCE.popupContainer().toString());
        popupPanel.add(popupContents);
        addShowListBoxHandler(baseContainer);

        this.delays = delays;
    }

    @Override
    public void addOpenCloseListener(ExListBoxOpenCloseListener openCloseListener) {
        this.openCloseListener = openCloseListener;
        popupPanel.addCloseHandler(openCloseListener);
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
                    event.preventDefault();
                    updateOptionButtonsSelection();
                    popupPanel.show();
                    fireOpenEvent();
                    updatePosition();
                    setAutoHideWithDelay();
                }
            }
        };
        return showListBox;
    }

    private void setAutoHideWithDelay() {
        Timer timer = new Timer() {

            @Override
            public void run() {
                popupPanel.setAutoHideEnabled(true);
            }
        };
        timer.schedule(delays.getCloseDelay());
    }

    private void fireOpenEvent() {
        if (openCloseListener != null) {
            openCloseListener.onOpen();
        }
    }

    @Override
    public void addOption(IsWidget baseBody, IsWidget popupBody) {
        addOption(new ExListBoxOption(baseBody, popupBody));
    }

    @Override
    public void addOption(final ExListBoxOption option) {
        options.add(option);
        popupContents.addOption(option.getPopupBody());
        selectionController.addOption(option);
    }

    protected void selectOption(final ExListBoxOption option) {
        int currentOptionIndex = getOptionIndex(option);
        if (selectedIndex != currentOptionIndex) {
            selectedIndex = currentOptionIndex;
        }
        setSelectedBodyAndFireOnChange();
    }

    private void setSelectedBodyAndFireOnChange() {
        setSelectedBaseBody();
        listener.onChange();
    }

    private int getOptionIndex(final ExListBoxOption option) {
        int currentOptionIndex = options.indexOf(option);
        return currentOptionIndex;
    }

    @Override
    public void setChangeListener(ExListBoxChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

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
        Timer timer = new Timer() {

            @Override
            public void run() {
                popupPanel.hide();
            }
        };
        timer.schedule(delays.getCloseDelay());
    }

    private void updatePosition() {
        int mountingPointX = 0;
        int mountingPointY = 0;
        final int MARGIN = 8;

        mountingPointX = baseContainer.getAbsoluteLeft() + baseContainer.getOffsetWidth() / 2 - popupPanel.getOffsetWidth() / 2;
        if (popupPosition == PopupPosition.ABOVE) {
            mountingPointY = baseContainer.getAbsoluteTop() + baseContainer.getOffsetHeight() - popupPanel.getOffsetHeight();
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

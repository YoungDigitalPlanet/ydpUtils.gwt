package eu.ydp.gwtutil.client.components.exlistbox;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Custom combo box widget that display list of options on the popup.
 * 
 * Use {@link #addOption(IsWidget, IsWidget)} to add options.
 * 
 * @author rrybacki@ydp.com.pl
 */
// TODO commit
public class ExListBox extends Composite implements IExListBox {

	private static ExListBoxUiBinder uiBinder = GWT.create(ExListBoxUiBinder.class);

	interface ExListBoxUiBinder extends UiBinder<Widget, ExListBox> {}
	
	public static enum PopupPosition {ABOVE, BELOW};

	@UiField FlowPanel mainContainer;
	@UiField FocusPanel baseContainer;
	@UiField FlowPanel baseContainerInner;
	@UiField FlowPanel baseContents;
	
	private ExListBoxPopup popupContents;
	private PopupPanel popupPanel;
	private List<ExListBoxOption> options;
	
	private int selectedIndex = 0;
	private boolean enabled = true;
	private PopupPosition popupPosition = PopupPosition.ABOVE;
	
	protected ExListBoxChangeListener listener;

	public ExListBox(){
		initWidget(uiBinder.createAndBindUi(this));
		
		options = new ArrayList<ExListBoxOption>();
		
		popupContents = new ExListBoxPopup();

		popupPanel = new PopupPanel(true);
		popupPanel.setStyleName(ExListBoxStyleNames.INSTANCE.popupContainer().toString());
		popupPanel.add(popupContents);
	}
	
	@UiHandler("baseContainer")
	public void baseContainerClickHandler(ClickEvent arg0){
		if (enabled){
			updateOptionButtonsSelection();
			popupPanel.show();
			updatePosition();
		}
		
	}

	public void addOption(IsWidget baseBody, IsWidget popupBody){
		addOption(new ExListBoxOption(baseBody, popupBody));
	}
	
	public void addOption(final ExListBoxOption option){
		options.add(option);
		popupContents.addOption(option.getPopupBody());
		option.getPopupBody().addDomHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				selectedIndex = options.indexOf(option);
				setSelectedBaseBody();
				listener.onChange();
			}
		}, ClickEvent.getType());
	}
	
	public void setChangeListener(ExListBoxChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * @return Index of the currently selected option or <code>-1</code> if no  option is selected.
	 */
	public int getSelectedIndex(){
		return selectedIndex;
	}
	
	/**
	 * @param index Index of the option to select or <code>-1</code>.
	 */
	public void setSelectedIndex(int index){
		if (index >= -1   &&  index < options.size()){
			selectedIndex = index;
			setSelectedBaseBody();
		}
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
		if (this.enabled){
			removeStyleName(ExListBoxStyleNames.INSTANCE.disabled().toString());
		} else {
			addStyleName(ExListBoxStyleNames.INSTANCE.disabled().toString());
		}
	}

	public boolean isEnabled(){
		return this.enabled;
	}

	public void setPopupPosition(PopupPosition pp){
		popupPosition = pp;
	}

	public PopupPosition getPopupPosition(){
		return popupPosition;
	}
	
	private void setSelectedBaseBody(){
		baseContents.clear();
		if (selectedIndex >= 0  &&  selectedIndex < options.size()){
			baseContents.add(options.get(selectedIndex).getBaseBody());
		}
		popupPanel.hide();		
	}

	private void updatePosition(){
		int mountingPointX = 0;
		int mountingPointY = 0;
		final int MARGIN = 8;
		
		mountingPointX = baseContainer.getAbsoluteLeft() + baseContainer.getOffsetWidth()/2 - popupPanel.getOffsetWidth()/2;
		if (popupPosition == PopupPosition.ABOVE){
			mountingPointY = baseContainer.getAbsoluteTop() - popupPanel.getOffsetHeight();
		} else { 
			mountingPointY = baseContainer.getAbsoluteTop() + baseContainer.getOffsetHeight();
		}

		if (mountingPointX < Window.getScrollLeft() + MARGIN){
			mountingPointX = Window.getScrollLeft() + MARGIN;
		} else if (mountingPointX + popupPanel.getOffsetWidth() > Window.getClientWidth() + Window.getScrollLeft() + MARGIN){
			mountingPointX = Window.getClientWidth() + Window.getScrollLeft() + MARGIN - popupPanel.getOffsetWidth();
		}

		if (mountingPointY < Window.getScrollTop() + MARGIN){
			mountingPointY = Window.getScrollTop() + MARGIN;
		} else if (mountingPointY + popupPanel.getOffsetHeight() > Window.getClientHeight() + Window.getScrollTop() + MARGIN){
			mountingPointY = Window.getClientHeight() + Window.getScrollTop() + MARGIN - popupPanel.getOffsetHeight();
		}
				
		popupPanel.setPopupPosition(mountingPointX, mountingPointY);
		
	}
	
	private void updateOptionButtonsSelection(){
		for (int i = 0 ; i < options.size() ; i ++){
			options.get(i).setSelected(i == selectedIndex);
		}
	}
	
    /**
     * <p>Exposes private methods for unit tests. This class must not be used
     * for any purpose other than unit testing.</p>
     */	
	public final class ExListBoxUnitTestAccess {
	
		public void setOptions(ArrayList<ExListBoxOption> options) {
			ExListBox.this.options = options;
		}		
	}
	
}
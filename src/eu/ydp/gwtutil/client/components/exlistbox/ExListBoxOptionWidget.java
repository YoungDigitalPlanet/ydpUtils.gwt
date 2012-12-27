package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ExListBoxOptionWidget extends Composite {

	private static ExListBoxOptionPopupWidgetUiBinder uiBinder = GWT.create(ExListBoxOptionPopupWidgetUiBinder.class);

	interface ExListBoxOptionPopupWidgetUiBinder extends UiBinder<Widget, ExListBoxOptionWidget> { }

	@UiField FocusPanel popupPanel;
	@UiField FlowPanel popupContainer;
	@UiField FocusPanel popupButton;
	
	private boolean selected = false;
	private boolean over = false;

	public ExListBoxOptionWidget(IsWidget popupBody) {
		initWidget(uiBinder.createAndBindUi(this));
		
		popupContainer.insert(popupBody, 0);
	}
	
	@UiHandler("popupPanel")
	public void onMouseOver(MouseOverEvent event) {
		setOver(true);				
	}
	
	@UiHandler("popupPanel")
	public void onMouseOut(MouseOutEvent event) {
		setOver(false);
	}
	
	@UiHandler("popupPanel")
	public void onClick(ClickEvent event) {
		setOver(false);
	}
	
	protected void setOver(boolean over){
		this.over = over;
		updateStyle();
	}
		
	public void setSelected(boolean sel){
		this.selected = sel;
		updateStyle();
	}
	
	private void updateStyle(){
		ExListBoxStyleNames popupPanelStyleName;
		if (selected  &&  over) {
			popupPanelStyleName = ExListBoxStyleNames.INSTANCE.popupOptionPanelSelectedOver();
		} else if (selected) {
			popupPanelStyleName = ExListBoxStyleNames.INSTANCE.popupOptionPanelSelected();
		} else if (over){
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

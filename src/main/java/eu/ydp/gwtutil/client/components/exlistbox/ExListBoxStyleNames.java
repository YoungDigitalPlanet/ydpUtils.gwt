package eu.ydp.gwtutil.client.components.exlistbox;

import com.google.gwt.core.client.GWT;
import eu.ydp.gwtutil.client.constants.Prefix;
import eu.ydp.gwtutil.client.constants.Separator;
import eu.ydp.gwtutil.client.constants.StringConstants;
import eu.ydp.gwtutil.client.constants.Suffix;

@Prefix("qp-exlistbox")
@Suffix("")
@Separator("-")
public interface ExListBoxStyleNames extends StringConstants {

    ExListBoxStyleNames INSTANCE = GWT.create(ExListBoxStyleNames.class);

    ExListBoxStyleNames baseButton();

    ExListBoxStyleNames baseContainer();

    ExListBoxStyleNames baseContainerInner();

    ExListBoxStyleNames baseContents();

    ExListBoxStyleNames disabled();

    ExListBoxStyleNames popupContainer();

    ExListBoxStyleNames popupContents();

    ExListBoxStyleNames popupInnerContainer();

    ExListBoxStyleNames popupOptionButton();

    ExListBoxStyleNames popupOptionButtonSelected();

    ExListBoxStyleNames popupOptionPanel();

    ExListBoxStyleNames popupOptionPanelSelected();

    ExListBoxStyleNames popupOptionPanelOver();

    ExListBoxStyleNames popupOptionPanelSelectedOver();

    ExListBoxStyleNames popupOptionPanelInner();

    ExListBoxStyleNames popupOuterContainer();

    ExListBoxStyleNames popupOuterFooter();

    ExListBoxStyleNames popupOuterHeader();
}

package eu.ydp.gwtutil.client.components.exlistbox;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.dom.client.NativeEvent;

public class ExListBoxSelectionController implements ExListBoxClickHandler, ExListBoxOverHandler {
	private final ExListBox listBox;

	private final List<ExListBoxOption> options = Lists.newArrayList();

	public ExListBoxSelectionController(ExListBox listBox) {
		this.listBox = listBox;
	}

	public void addOption(ExListBoxOption listBoxOption) {
		options.add(listBoxOption);
		listBoxOption.setClickHandler(this);
		listBoxOption.setOverHandler(this);
	}

	@Override
	public void onEvent(boolean over, ExListBoxOption selectedOption) {
		selectedOption.setOver(over);
		for (ExListBoxOption option : options) {
			if (!option.equals(selectedOption)) {
				option.setOver(false);
			}
		}
	}

	@Override
	public void onClick(NativeEvent event, ExListBoxOption selectedOption) {
		selectedOption.setSelected(true);
		for (ExListBoxOption option : options) {
			if (!option.equals(selectedOption)) {
				option.reset();
			}
		}
		listBox.selectOption(selectedOption);
		listBox.hidePopup();
	}
}

package eu.ydp.gwtutil.client.xml;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class EmptyNodeList implements NodeList {

	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public Node item(int index) {
		throw new IndexOutOfBoundsException("EmptyNodeList does not contain any items.");
	}

}

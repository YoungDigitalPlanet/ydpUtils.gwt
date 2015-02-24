package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;

public class NamedNodeMapWrapper implements NamedNodeMap {

	private org.w3c.dom.NamedNodeMap namedNodeMap;

	public NamedNodeMapWrapper(org.w3c.dom.NamedNodeMap namedNodeMap) {
		this.namedNodeMap = namedNodeMap;
	}

	@Override
	public int getLength() {
		return namedNodeMap.getLength();
	}

	@Override
	public Node getNamedItem(String name) {
		return NodeWrapperFactory.createNode(namedNodeMap.getNamedItem(name));
	}

	@Override
	public Node item(int index) {
		return NodeWrapperFactory.createNode(namedNodeMap.item(index));
	}

}

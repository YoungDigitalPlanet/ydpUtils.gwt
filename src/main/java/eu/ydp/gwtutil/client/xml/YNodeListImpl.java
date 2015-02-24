package eu.ydp.gwtutil.client.xml;

import java.util.ArrayList;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class YNodeListImpl implements NodeList {

	private ArrayList<Element> nodes;

	public YNodeListImpl() {
		nodes = new ArrayList<Element>();
	}

	public void add(Element e) {
		nodes.add(e);
	}

	@Override
	public int getLength() {
		return nodes.size();
	}

	@Override
	public Node item(int index) {
		return nodes.get(index);
	}

}

package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class NodeListWrapper implements NodeList {

    org.w3c.dom.NodeList nodeList;

    public NodeListWrapper(org.w3c.dom.NodeList nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public int getLength() {
        return nodeList.getLength();
    }

    @Override
    public Node item(int index) {
        return NodeWrapperFactory.createNode(nodeList.item(index));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            sb.append(nodeList.item(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}

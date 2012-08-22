package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class NodeWrapper implements Node {

	protected org.w3c.dom.Node node;

	public NodeWrapper(org.w3c.dom.Node node){
		this.node = node;
	}
	
	@Override
	public Node appendChild(Node newChild) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Node cloneNode(boolean deep) {
		return NodeWrapperFactory.createNode(node.cloneNode(deep));
	}

	@Override
	public NamedNodeMap getAttributes() {
		return new NamedNodeMapWrapper(node.getAttributes());
	}

	@Override
	public NodeList getChildNodes() {
		return new NodeListWrapper(node.getChildNodes());
	}

	@Override
	public Node getFirstChild() {
		return NodeWrapperFactory.createNode(node.getFirstChild());
	}

	@Override
	public Node getLastChild() {
		return NodeWrapperFactory.createNode(node.getLastChild());
	}

	@Override
	public String getNamespaceURI() {
		return node.getNamespaceURI();
	}

	@Override
	public Node getNextSibling() {
		return NodeWrapperFactory.createNode(node.getNextSibling());
	}

	@Override
	public String getNodeName() {
		return node.getNodeName();
	}

	@Override
	public short getNodeType() { // NOPMD
		return node.getNodeType();
	}

	@Override
	public String getNodeValue() {
		return node.getNodeValue();
	}

	@Override
	public Document getOwnerDocument() {
		return new DocumentWrapper(node.getOwnerDocument());
	}

	@Override
	public Node getParentNode() {
		return NodeWrapperFactory.createNode(node.getParentNode());
	}

	@Override
	public String getPrefix() {
		return node.getPrefix();
	}

	@Override
	public Node getPreviousSibling() {
		return NodeWrapperFactory.createNode(node.getPreviousSibling());
	}

	@Override
	public boolean hasAttributes() {
		return node.hasAttributes();
	}

	@Override
	public boolean hasChildNodes() {
		return node.hasChildNodes();
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void normalize() {
		node.normalize();
	}

	@Override
	public Node removeChild(Node oldChild) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setNodeValue(String nodeValue) {
		throw new UnsupportedOperationException();
	}

}

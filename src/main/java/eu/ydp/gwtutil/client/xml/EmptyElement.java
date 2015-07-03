package eu.ydp.gwtutil.client.xml;

import com.google.gwt.xml.client.*;

public class EmptyElement implements Element {

    private static final String NOT_SUPPORTED = "Not supported in EmptyElement.";
    private String nodeName;

    public EmptyElement(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public Node appendChild(Node newChild) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public Node cloneNode(boolean deep) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public NamedNodeMap getAttributes() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public NodeList getChildNodes() {
        return new EmptyNodeList();
    }

    @Override
    public Node getFirstChild() {
        return null;
    }

    @Override
    public Node getLastChild() {
        return null;
    }

    @Override
    public String getNamespaceURI() {
        return "";
    }

    @Override
    public Node getNextSibling() {
        return null;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public short getNodeType() {
        return Node.ELEMENT_NODE;
    }

    @Override
    public String getNodeValue() {
        return "";
    }

    @Override
    public Document getOwnerDocument() {
        return null;
    }

    @Override
    public Node getParentNode() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public Node getPreviousSibling() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public boolean hasChildNodes() {
        return false;
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void normalize() {
    }

    @Override
    public Node removeChild(Node oldChild) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void setNodeValue(String nodeValue) {
    }

    @Override
    public String getAttribute(String name) {
        return "";
    }

    @Override
    public Attr getAttributeNode(String name) {
        return null;
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        return new EmptyNodeList();
    }

    @Override
    public String getTagName() {
        return nodeName;
    }

    @Override
    public boolean hasAttribute(String name) {
        return false;
    }

    @Override
    public void removeAttribute(String name) {
    }

    @Override
    public void setAttribute(String name, String value) {
    }

}

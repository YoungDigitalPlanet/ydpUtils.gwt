package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.CDATASection;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.DocumentFragment;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.ProcessingInstruction;
import com.google.gwt.xml.client.Text;

public class DocumentWrapper extends NodeWrapper implements Document {
	
	private org.w3c.dom.Document document;

	public DocumentWrapper(org.w3c.dom.Document document){
		super(document);
		this.document = document;
	}

	@Override
	public CDATASection createCDATASection(String data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Comment createComment(String data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DocumentFragment createDocumentFragment() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Element createElement(String tagName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ProcessingInstruction createProcessingInstruction(String target, String data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Text createTextNode(String data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Element getDocumentElement() {
		return new ElementWrapper(document.getDocumentElement());
	}

	@Override
	public Element getElementById(String elementId) {
		return new ElementWrapper(document.getElementById(elementId));
	}

	@Override
	public NodeList getElementsByTagName(String tagname) {
		return new NodeListWrapper(document.getElementsByTagName(tagname));
	}

	@Override
	public Node importNode(Node importedNode, boolean deep) {
		throw new UnsupportedOperationException();
	}
}

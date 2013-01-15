package eu.ydp.gwtutil.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;

import com.google.gwt.xml.client.Document;

public final class XMLParser {

	private XMLParser() {
	}

	public static Document parse(String contents) {
		return new DocumentWrapper(parseW3c(contents));
	}

	public static Document createDocument() {
		try {
			return new DocumentWrapper(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static org.w3c.dom.Document parseW3c(String contents) {
		try {
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(contents));
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

package eu.ydp.gwtutil.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

import com.google.gwt.xml.client.Document;

public final class XMLParser {
	
	private XMLParser(){}
	
	public static Document parse(String contents){		
		return new DocumentWrapper(parseW3c(contents));
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
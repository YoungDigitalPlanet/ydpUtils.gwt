package eu.ydp.gwtutil.client.xml;

import com.google.gwt.xml.client.Document;

public class XMLParser {
	public Document parse(String xmlConntent){
		return com.google.gwt.xml.client.XMLParser.parse(xmlConntent);
	}
}

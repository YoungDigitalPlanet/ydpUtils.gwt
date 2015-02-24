package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.Document;

import eu.ydp.gwtutil.client.xml.IXMLParser;

public class NativeXMLParser implements IXMLParser {

	@Override
	public Document parse(String xmlConntent) {
		return XMLParser.parse(xmlConntent);
	}

}

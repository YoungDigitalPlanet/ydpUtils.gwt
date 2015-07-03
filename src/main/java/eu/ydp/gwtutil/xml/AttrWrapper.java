package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.Attr;

public class AttrWrapper extends NodeWrapper implements Attr {

    public AttrWrapper(org.w3c.dom.Attr attr) {
        super(attr);
        this.node = attr;
    }

    private org.w3c.dom.Attr getAttr() {
        return (org.w3c.dom.Attr) node;
    }

    @Override
    public String getName() {
        return getAttr().getName();
    }

    @Override
    public boolean getSpecified() {
        return getAttr().getSpecified();
    }

    @Override
    public String getValue() {
        return getAttr().getValue();
    }
}

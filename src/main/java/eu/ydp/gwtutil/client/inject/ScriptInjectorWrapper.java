package eu.ydp.gwtutil.client.inject;

import com.google.gwt.core.client.ScriptInjector;

public class ScriptInjectorWrapper {

    public void fromUrl(final String url) {
        ScriptInjector.fromUrl(url).setWindow(ScriptInjector.TOP_WINDOW).inject();
    }

    public void fromString(String text) {
        ScriptInjector.fromString(text).setWindow(ScriptInjector.TOP_WINDOW).inject();
    }
}

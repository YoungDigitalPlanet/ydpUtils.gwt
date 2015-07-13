package eu.ydp.gwtutil.client;

import com.google.gwt.core.client.EntryPoint;
import eu.ydp.gwtutil.client.inject.ScriptInjectorWrapper;
import eu.ydp.gwtutil.client.util.paths.UrlConverter;

public class UtilsEntryPoint implements EntryPoint {

    private final ScriptInjectorWrapper scriptInjectorWrapper;
    private final UrlConverter urlConverter;

    public UtilsEntryPoint() {
        scriptInjectorWrapper = new ScriptInjectorWrapper();
        urlConverter = new UrlConverter();
    }

    @Override
    public void onModuleLoad() {
        injectScript("swfobject/swfobject.js");
    }

    private void injectScript(String path) {
        String correctUrl = urlConverter.getModuleRelativeUrl(path);
        scriptInjectorWrapper.fromUrl(correctUrl);
    }
}

package eu.ydp.gwtutil.client.util.paths;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;
import eu.ydp.gwtutil.client.scripts.ScriptUrl;

@Singleton
public class UrlConverter {

    public String getModuleRelativeUrl(String url) {
        return GWT.getModuleBaseURL() + url;
    }

    public String getModuleRelativeUrl(ScriptUrl url) {
        return GWT.getModuleBaseURL() + url.getUrl();
    }
}

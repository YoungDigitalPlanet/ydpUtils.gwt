package eu.ydp.gwtutil.client.inject;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

public class ScriptInjectorWrapper {

	public void fromUrl(final String url) {
		ScriptInjector.fromUrl(url).inject();
	}

	public void fromUrl(String url, Callback<Void, Exception> callback) {
		ScriptInjector.fromUrl(url).setCallback(callback).inject();
	}
}

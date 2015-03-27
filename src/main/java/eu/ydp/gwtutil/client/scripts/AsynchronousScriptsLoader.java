package eu.ydp.gwtutil.client.scripts;

import com.google.inject.Inject;
import eu.ydp.gwtutil.client.inject.ScriptInjectorWrapper;
import eu.ydp.gwtutil.client.util.paths.UrlConverter;

public class AsynchronousScriptsLoader {

	@Inject
	private ScriptInjectorWrapper scriptInjectorWrapper;
	@Inject
	private UrlConverter urlConverter;

	public void inject(ScriptUrl[] scripts) {
		for (ScriptUrl script : scripts) {
			String correctUrl = urlConverter.getModuleRelativeUrl(script.getUrl());
			scriptInjectorWrapper.fromUrl(correctUrl);
		}
	}
}

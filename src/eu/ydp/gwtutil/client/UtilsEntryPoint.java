package eu.ydp.gwtutil.client;

import com.google.gwt.core.client.EntryPoint;

import eu.ydp.gwtutil.client.inject.ScriptInjectorWrapper;

public class UtilsEntryPoint implements EntryPoint{

	private final ScriptInjectorWrapper scriptInjectorWrapper;

	public UtilsEntryPoint() {
		scriptInjectorWrapper = new ScriptInjectorWrapper();
	}

	@Override
	public void onModuleLoad() {
		scriptInjectorWrapper.fromUrl("swfobject/swfobject.js");
	}
}

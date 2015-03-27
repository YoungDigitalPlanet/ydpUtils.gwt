package eu.ydp.gwtutil.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public interface NativeMethodInvocator {
	void callMethod(JavaScriptObject target, String methodName);
}
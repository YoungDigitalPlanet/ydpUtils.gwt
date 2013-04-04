package eu.ydp.gwtutil.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class TestObject extends JavaScriptObject{
	public static String METHOD_NAME = "test";

	protected TestObject() {

	}

	public final native void init()/*-{
		var thiss = this;
		this.test = function(){
			thiss.exec = true;
		}
	}-*/;


	public final native boolean isExec()/*-{
		return this.exec;
	}-*/;
}

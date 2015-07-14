package eu.ydp.gwtutil.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class NativeMethodInvocatorImpl implements NativeMethodInvocator {

    private native void callNativeMethod(JavaScriptObject context, String methodName) /*-{
        try {
            if (typeof arguments[0][methodName] == 'function') {
                var args = Array.prototype.slice.call(arguments, 2);
                var namespaces = methodName.split(".");
                var func = namespaces.pop();
                for (var i = 0; i < namespaces.length; i++) {
                    context = context[namespaces[i]];
                }
                context[func].apply(context, args);
            }
        } catch (e) {
            //console.log(e);
        }
    }-*/;

    @Override
    public void callMethod(JavaScriptObject target, String methodName) {
        this.callNativeMethod(target, methodName);
    }

}

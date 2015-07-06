package eu.ydp.gwtutil.client.scripts;

import com.google.common.collect.Lists;
import com.google.gwt.core.client.Callback;

import com.google.inject.Inject;

import java.util.*;

public class SynchronousScriptsLoader {

    @Inject
    private AsynchronousScriptsLoader asynchronousScriptsLoader;

    public void injectScripts(ScriptUrl[] scripts, Callback<Void, Exception> callback) {

        List<ScriptUrl> scriptsList = Lists.newArrayList(scripts);
        Collections.reverse(scriptsList);

        Stack<ScriptUrl> scriptsStack = new Stack<>();
        scriptsStack.addAll(scriptsList);
        injectScript(scriptsStack, callback);

    }

    private void injectScript(final Stack<ScriptUrl> scriptsStack, final Callback<Void, Exception> callback) {

        asynchronousScriptsLoader.inject(scriptsStack.pop(), new Callback<Void, Exception>() {
            @Override
            public void onFailure(Exception reason) {

            }

            @Override
            public void onSuccess(Void result) {
                if (scriptsStack.empty()) {
                    callback.onSuccess(result);
                } else {
                    injectScript(scriptsStack, callback);
                }
            }
        });

    }

}

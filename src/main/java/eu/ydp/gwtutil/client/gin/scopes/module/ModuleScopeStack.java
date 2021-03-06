package eu.ydp.gwtutil.client.gin.scopes.module;

import com.google.inject.Singleton;

import java.util.Stack;

@Singleton
public class ModuleScopeStack {

    private final Stack<ModuleCreationContext> contextsStack = new Stack<ModuleCreationContext>();

    public void pushContext(ModuleCreationContext context) {
        contextsStack.push(context);
    }

    public ModuleCreationContext getCurrentTopContext() {
        return contextsStack.peek();
    }

    public ModuleCreationContext pop() {
        return contextsStack.pop();
    }
}

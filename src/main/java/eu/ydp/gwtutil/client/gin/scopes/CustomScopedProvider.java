package eu.ydp.gwtutil.client.gin.scopes;

import com.google.inject.Provider;

import java.util.HashMap;
import java.util.Map;

public class CustomScopedProvider<T> implements Provider<T> {

    private Provider<T> provider;
    private CurrentScopeProvider currentScopeProvider;
    private Map<AbstractCustomScope, T> scopedInstancesMap = new HashMap<AbstractCustomScope, T>();

    public CustomScopedProvider(Provider<T> provider, CurrentScopeProvider currentScopeProvider) {
        this.provider = provider;
        this.currentScopeProvider = currentScopeProvider;
    }

    @Override
    public T get() {
        AbstractCustomScope currentScope = currentScopeProvider.getCurrentScope();

        T instance = scopedInstancesMap.get(currentScope);
        if (instance == null) {
            instance = provider.get();
            scopedInstancesMap.put(currentScope, instance);
        }

        return instance;
    }

}

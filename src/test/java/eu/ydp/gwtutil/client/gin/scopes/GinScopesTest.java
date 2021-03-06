package eu.ydp.gwtutil.client.gin.scopes;

import com.google.inject.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GinScopesTest {

    @Test
    public void testGinScopesTest() throws Exception {

        Injector injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {
                bind(TestClassScopedProvider.class).in(Singleton.class);
                bind(TestClass.class).annotatedWith(TestScoped.class).toProvider(TestClassScopedProvider.class);
                bind(CurrentScopeProvider.class).to(TestCurrentScopeProvider.class).in(Singleton.class);
            }
        });

        TestClass pageScopedInstance = injector.getInstance(Key.get(TestClass.class, TestScoped.class));
        TestClass pageScopedInstance2 = injector.getInstance(Key.get(TestClass.class, TestScoped.class));
        assertTrue(pageScopedInstance == pageScopedInstance2);

        TestClass notPageScopedinstance = injector.getInstance(TestClass.class);
        assertTrue(pageScopedInstance != notPageScopedinstance);
    }

}

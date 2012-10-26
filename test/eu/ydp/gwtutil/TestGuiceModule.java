package eu.ydp.gwtutil;

import eu.ydp.gwtutil.client.xml.proxy.XMLProxy;
import eu.ydp.gwtutil.client.xml.proxy.XMLProxyFactory;
import eu.ydp.gwtutil.test.AbstractMockingTestModule;
import eu.ydp.gwtutil.xml.XMLProxyWrapper;

public class TestGuiceModule extends AbstractMockingTestModule {

	public TestGuiceModule(){
		super();
	}
	
	public TestGuiceModule(Class<?>... classToOmit){
		super(classToOmit);
	}
	
	@Override
	public void configure() {
		bind(XMLProxy.class).to(XMLProxyWrapper.class);
		binder.requestStaticInjection(XMLProxyFactory.class);
	}

}

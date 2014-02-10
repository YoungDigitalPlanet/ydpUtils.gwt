package eu.ydp.gwtutil.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;

import eu.ydp.gwtutil.client.util.BrowserNativeInterface;
import eu.ydp.gwtutil.client.util.BrowserNativeInterfaceImpl;
import eu.ydp.gwtutil.client.util.UserAgentUtil;
import eu.ydp.gwtutil.client.util.UserAgentUtilImpl;

public class UtilGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BrowserNativeInterface.class).to(BrowserNativeInterfaceImpl.class);
		bind(UserAgentUtil.class).to(UserAgentUtilImpl.class);
	}
}

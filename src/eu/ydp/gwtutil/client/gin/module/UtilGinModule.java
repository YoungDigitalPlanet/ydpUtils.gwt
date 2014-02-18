package eu.ydp.gwtutil.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;

import eu.ydp.gwtutil.client.util.AudioWrapper;
import eu.ydp.gwtutil.client.util.AudioWrapperImpl;
import eu.ydp.gwtutil.client.util.BrowserNativeInterface;
import eu.ydp.gwtutil.client.util.BrowserNativeInterfaceImpl;
import eu.ydp.gwtutil.client.util.MediaChecker;
import eu.ydp.gwtutil.client.util.MediaCheckerImpl;
import eu.ydp.gwtutil.client.util.UserAgentUtil;
import eu.ydp.gwtutil.client.util.UserAgentUtilImpl;

public class UtilGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BrowserNativeInterface.class).to(BrowserNativeInterfaceImpl.class);
		bind(UserAgentUtil.class).to(UserAgentUtilImpl.class);
		bind(MediaChecker.class).to(MediaCheckerImpl.class);
		bind(AudioWrapper.class).to(AudioWrapperImpl.class);
	}
}

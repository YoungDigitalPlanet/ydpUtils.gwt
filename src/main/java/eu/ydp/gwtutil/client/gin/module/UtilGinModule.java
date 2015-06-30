package eu.ydp.gwtutil.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import eu.ydp.gwtutil.client.inject.ScriptInjectorWrapper;
import eu.ydp.gwtutil.client.scripts.AsynchronousScriptsLoader;
import eu.ydp.gwtutil.client.util.*;
import eu.ydp.gwtutil.client.util.paths.UrlConverter;
import eu.ydp.gwtutil.client.xml.IXMLParser;
import eu.ydp.gwtutil.client.xml.XMLParser;

public class UtilGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(BrowserNativeInterface.class).to(BrowserNativeInterfaceImpl.class);
        bind(UserAgentUtil.class).to(UserAgentUtilImpl.class);
        bind(MediaChecker.class).to(MediaCheckerImpl.class);
        bind(AudioWrapper.class).to(AudioWrapperImpl.class);
        bind(IXMLParser.class).to(XMLParser.class);

        bind(UrlConverter.class).in(Singleton.class);
        bind(ScriptInjectorWrapper.class).in(Singleton.class);
        bind(AsynchronousScriptsLoader.class).in(Singleton.class);
    }
}

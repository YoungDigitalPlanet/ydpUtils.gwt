package eu.ydp.gwtutil.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import eu.ydp.gwtutil.client.components.exlistbox.ExListBoxDelays;
import eu.ydp.gwtutil.client.gin.scopes.module.ModuleScopeStack;
import eu.ydp.gwtutil.client.inject.ScriptInjectorWrapper;
import eu.ydp.gwtutil.client.json.NativeMethodInvocator;
import eu.ydp.gwtutil.client.json.NativeMethodInvocatorImpl;
import eu.ydp.gwtutil.client.json.js.YJsJsonConverter;
import eu.ydp.gwtutil.client.scheduler.Scheduler;
import eu.ydp.gwtutil.client.scheduler.SchedulerImpl;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.service.json.JSONService;
import eu.ydp.gwtutil.client.timer.Timer;
import eu.ydp.gwtutil.client.timer.TimerImpl;
import eu.ydp.gwtutil.client.ui.GWTPanelFactory;
import eu.ydp.gwtutil.client.ui.GWTPanelFactoryImpl;
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
        bind(Scheduler.class).to(SchedulerImpl.class);
        bind(GWTPanelFactory.class).to(GWTPanelFactoryImpl.class);
        bind(IJSONService.class).to(JSONService.class);
        bind(Timer.class).to(TimerImpl.class);
        bind(NativeMethodInvocator.class).to(NativeMethodInvocatorImpl.class);
    }
}

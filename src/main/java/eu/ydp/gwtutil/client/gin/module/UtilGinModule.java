package eu.ydp.gwtutil.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;
import eu.ydp.gwtutil.client.animation.js.FrameworkAnimation;
import eu.ydp.gwtutil.client.animation.js.FrameworkAnimationNative;
import eu.ydp.gwtutil.client.json.NativeMethodInvocator;
import eu.ydp.gwtutil.client.json.NativeMethodInvocatorImpl;
import eu.ydp.gwtutil.client.scheduler.Scheduler;
import eu.ydp.gwtutil.client.scheduler.SchedulerImpl;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.service.json.JSONService;
import eu.ydp.gwtutil.client.timer.Timer;
import eu.ydp.gwtutil.client.timer.TimerImpl;
import eu.ydp.gwtutil.client.ui.GWTPanelFactory;
import eu.ydp.gwtutil.client.ui.GWTPanelFactoryImpl;
import eu.ydp.gwtutil.client.util.*;
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
        bind(FrameworkAnimation.class).to(FrameworkAnimationNative.class);
    }
}

package eu.ydp.gwtutil.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import eu.ydp.gwtutil.client.animation.css.CssAnimationClassBuilder;
import eu.ydp.gwtutil.client.animation.js.FrameworkAnimation;
import eu.ydp.gwtutil.client.animation.js.FrameworkAnimationNative;

public class AnimationGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(FrameworkAnimation.class).to(FrameworkAnimationNative.class);
        bind(CssAnimationClassBuilder.class).in(Singleton.class);
    }

}

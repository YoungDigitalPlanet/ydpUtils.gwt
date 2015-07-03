package eu.ydp.gwtutil.client.animation;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import eu.ydp.gwtutil.client.animation.holder.AnimationHolder;
import eu.ydp.gwtutil.client.animation.js.AnimationAnalyzer;
import eu.ydp.gwtutil.client.animation.preload.ImagePreloadHandler;
import eu.ydp.gwtutil.client.animation.preload.ImagePreloader;
import eu.ydp.gwtutil.client.util.geom.Size;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GenericAnimation implements Animation, AnimationEndHandler {
    private
    @Inject
    @Nonnull
    ImagePreloader imagePreloader;
    private
    @Inject
    @Nonnull
    AnimationAnalyzer animationAnalyzer;

    private AnimationEndHandler animationEndHandler;
    private AnimationWithRuntimeConfig realAnimation;
    private AnimationConfig animationConfig;
    private
    @Nullable
    HandlerRegistration currentPreloadHandlerRegistration;
    private AnimationHolder animationHolder;

    public void init(AnimationWithRuntimeConfig animation, AnimationConfig animationConfig, AnimationHolder holder) {
        this.realAnimation = animation;
        this.animationConfig = animationConfig;
        this.animationHolder = holder;
    }

    @Override
    public void start(AnimationEndHandler handler) {
        this.animationEndHandler = handler;
        preloadAndPlay();
    }

    private void preloadAndPlay() {
        String src = animationConfig.getSource();
        resetPreloadHandler();
        currentPreloadHandlerRegistration = imagePreloader.preload(src, getImagePreloadHandler());
    }

    private ImagePreloadHandler getImagePreloadHandler() {
        return new ImagePreloadHandler() {

            @Override
            public void onLoad(Size imageSize) {
                playAnimation(imageSize);
                currentPreloadHandlerRegistration = null;
            }

            @Override
            public void onError() {
                onEnd();
                currentPreloadHandlerRegistration = null;
            }
        };
    }

    private void resetPreloadHandler() {
        if (currentPreloadHandlerRegistration != null) {
            currentPreloadHandlerRegistration.removeHandler();
        }
    }

    private void playAnimation(Size imageSize) {
        int framesCount = animationAnalyzer.findFramesCount(imageSize, animationConfig.getFrameSize());
        if (framesCount > 0) {
            AnimationRuntimeConfig runtimeConfig = getAnimationRuntimeConfiguration(imageSize, framesCount);
            realAnimation.setRuntimeConfiguration(runtimeConfig);
            play();
        } else {
            onEnd();
        }
    }

    private AnimationRuntimeConfig getAnimationRuntimeConfiguration(Size imageSize, int framesCount) {
        return new AnimationRuntimeConfig(imageSize, framesCount, animationConfig, animationHolder);
    }

    private void play() {
        realAnimation.start(this);
    }

    @Override
    public void terminate() {
        resetPreloadHandler();
        realAnimation.terminate();
    }

    @Override
    public void onEnd() {
        if (animationEndHandler != null) {
            animationEndHandler.onEnd();
        }

    }
}

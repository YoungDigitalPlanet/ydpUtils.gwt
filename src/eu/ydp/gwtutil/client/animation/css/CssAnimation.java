package eu.ydp.gwtutil.client.animation.css;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Strings;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;

import eu.ydp.gwtutil.client.animation.AnimationConfig;
import eu.ydp.gwtutil.client.animation.AnimationEndHandler;
import eu.ydp.gwtutil.client.animation.AnimationRuntimeConfig;
import eu.ydp.gwtutil.client.animation.AnimationWithRuntimeConfig;
import eu.ydp.gwtutil.client.animation.holder.AnimationHolder;
import eu.ydp.gwtutil.client.util.geom.Size;

public class CssAnimation implements AnimationWithRuntimeConfig {
	private @Inject @Nonnull CssAnimationClassBuilder animationClassBuilder;
	private @Nullable AnimationEndHandler animationEndHandler;
	private @Nullable String currentAnimationClassName;
	private HandlerRegistration endHandlerRegistration;
	private AnimationRuntimeConfig animationRuntimeConfig;

	@Override
	public void start(AnimationEndHandler handler) {
		this.animationEndHandler = handler;
		play();
	}

	private void play() {
		currentAnimationClassName = animationClassBuilder.createAnimationCssClassName(getAnimationConfig(), animationRuntimeConfig.getImageSize());
		Size frameSize = getAnimationConfig().getFrameSize();
		endHandlerRegistration = getAnimationHolder().addAnimationEndHandler(new AnimationEndHandler() {

			@Override
			public void onEnd() {
				animationEnd();
			}
		});
		getAnimationHolder().setAnimationStyleName(currentAnimationClassName, frameSize);
	}

	private AnimationConfig getAnimationConfig() {
		return animationRuntimeConfig.getAnimationConfig();
	}

	private AnimationHolder getAnimationHolder() {
		return animationRuntimeConfig.getAnimationHolder();
	}

	@Override
	public void terminate() {
		removeAnimationStyleName();
		removeEndHandlerRegistrationIfExists();// Null protection added here for IOS. Unnecessary in other cases.
	}

	private void removeAnimationStyleName() {
		if (!Strings.isNullOrEmpty(currentAnimationClassName)) {
			getAnimationHolder().removeAnimationStyleName(currentAnimationClassName);
		}
	}

	private void animationEnd() {
		removeEndHandlerRegistrationIfExists();
		removeAnimationStyleName();
		if (animationEndHandler != null) {
			animationEndHandler.onEnd();
		}
	}
	
	private void removeEndHandlerRegistrationIfExists() {
		if (endHandlerRegistration != null) {
			endHandlerRegistration.removeHandler();
		}
	}

	@Override
	public void setRuntimeConfiguration(AnimationRuntimeConfig animationRuntimeConfig) {
		this.animationRuntimeConfig = animationRuntimeConfig;
	}

}

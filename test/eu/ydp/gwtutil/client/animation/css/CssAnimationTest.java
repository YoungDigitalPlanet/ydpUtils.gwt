package eu.ydp.gwtutil.client.animation.css;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.google.gwt.event.shared.HandlerRegistration;

import eu.ydp.gwtutil.client.animation.AnimationConfig;
import eu.ydp.gwtutil.client.animation.AnimationEndHandler;
import eu.ydp.gwtutil.client.animation.AnimationRuntimeConfig;
import eu.ydp.gwtutil.client.animation.holder.AnimationHolder;
import eu.ydp.gwtutil.client.util.geom.Size;

@RunWith(MockitoJUnitRunner.class)
public class CssAnimationTest {

	@Mock
	private AnimationEndHandler animationEndHandler;
	@Mock
	private	AnimationHolder animationHolder;
	@Mock
	private	CssAnimationClassBuilder cssAnimationClassBuilder;
	private @InjectMocks
	CssAnimation cssAnimation;
	
	private HandlerRegistration endHandlerRegistration;
	private AnimationRuntimeConfig animationRuntimeConfig;
	AnimationEndHandler inlineEndHandler;
	private Size FRAME_SIZE = new Size(20, 40);
	private final String ANIMATION_STYLE_NAME = "styleName";

	@Before
	public void setUp() {
		endHandlerRegistration = mock(HandlerRegistration.class);
		animationRuntimeConfig = createAnimationRuntimeConfig();
		cssAnimation.setRuntimeConfiguration(animationRuntimeConfig);
		mockInlineEndHandler();
	}

	@Test
	public void shouldPlayAnimationWhenStart() {
		mockAnimationStyleInBuilder();

		// when
		cssAnimation.start(animationEndHandler);

		// then
		verify(animationHolder).setAnimationStyleName(ANIMATION_STYLE_NAME, FRAME_SIZE);
	}


	@Test
	public void shouldRemoveAnimationStyleOnEndEvent() {
		mockAnimationStyleInBuilder();

		cssAnimation.start(animationEndHandler);

		// when
		inlineEndHandler.onEnd();

		// then
		verify(animationHolder).removeAnimationStyleName(ANIMATION_STYLE_NAME);
	}
	
	@Test
	public void shouldEndAnimationOnEndEvent() {
		mockAnimationStyleInBuilder();

		cssAnimation.start(animationEndHandler);

		// when
		inlineEndHandler.onEnd();

		// then
		verify(endHandlerRegistration).removeHandler();
		verify(animationEndHandler).onEnd();
	}

	
	@Test
	public void shouldRemoveAnimationStyleWhenTerminateAfterStart() {
		// given
		mockAnimationStyleInBuilder();
		cssAnimation.start(animationEndHandler);

		// when
		cssAnimation.terminate();

		// then
		verify(animationHolder).removeAnimationStyleName(ANIMATION_STYLE_NAME);
	}
	
	@Test
	public void shouldRemoveAnimationWhenTerminateAfterStart() {
		// given
		mockAnimationStyleInBuilder();
		cssAnimation.start(animationEndHandler);

		// when
		cssAnimation.terminate();

		// then
		verify(endHandlerRegistration).removeHandler();
		verify(animationEndHandler,never()).onEnd();
	}

	@Test
	public void shouldNotRemoveAnimationWhenTerminateWithoutStartBefore() {
		// given
		mockAnimationStyleInBuilder();

		// when
		cssAnimation.terminate();

		// then
		verify(animationHolder, never()).removeAnimationStyleName(anyString());
		verify(endHandlerRegistration, never()).removeHandler();

	}

	private AnimationRuntimeConfig createAnimationRuntimeConfig() {
		final String SOURCE = "image.png";
		final int FPS = 25;
		final Size IMAGE_SIZE = new Size(100, 40);
		final int FRAMES_COUNT = 5;

		AnimationConfig animationConfig = new AnimationConfig(FPS, FRAME_SIZE, SOURCE);
		AnimationRuntimeConfig animationRuntimeConfig = new AnimationRuntimeConfig(IMAGE_SIZE, FRAMES_COUNT, animationConfig, animationHolder);
		return animationRuntimeConfig;
	}

	private void mockInlineEndHandler() {
		doAnswer(new Answer<HandlerRegistration>() {
			@Override
			public HandlerRegistration answer(InvocationOnMock invocation) throws Throwable {
				inlineEndHandler = (AnimationEndHandler) invocation.getArguments()[0];
				endHandlerRegistration = mock(HandlerRegistration.class);
				return endHandlerRegistration;
			}
		}).when(animationRuntimeConfig.getAnimationHolder()).addAnimationEndHandler(Mockito.any(AnimationEndHandler.class));
	}

	private void mockAnimationStyleInBuilder() {
		doReturn(ANIMATION_STYLE_NAME).when(cssAnimationClassBuilder).createAnimationCssClassName(animationRuntimeConfig.getAnimationConfig(),
				animationRuntimeConfig.getImageSize());
	}
}

package eu.ydp.gwtutil.client.animation;

import com.google.gwt.event.shared.HandlerRegistration;
import eu.ydp.gwtutil.client.animation.holder.AnimationHolder;
import eu.ydp.gwtutil.client.animation.js.AnimationAnalyzer;
import eu.ydp.gwtutil.client.animation.preload.ImagePreloadHandler;
import eu.ydp.gwtutil.client.animation.preload.ImagePreloader;
import eu.ydp.gwtutil.client.util.geom.Size;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenericAnimationTest {
    private
    @Mock
    ImagePreloader imagePreloader;
    private
    @Mock
    AnimationAnalyzer animationAnalyzer;
    private
    @InjectMocks
    GenericAnimation instance;

    private
    @Mock
    AnimationWithRuntimeConfig animation;
    private
    @Mock
    AnimationHolder animationHolder;
    private AnimationConfig animationConfig;
    private AnimationEndHandler animationEndHandler;
    private Size frameSize;
    private String source;

    @Before
    public void before() {
        frameSize = new Size(20, 30);
        source = "http://dummy/jj.png";
        animationConfig = new AnimationConfig(20, frameSize, source);
        animationEndHandler = mock(AnimationEndHandler.class);
    }

    @Test
    public void start() throws Exception {
        instance.init(animation, animationConfig, animationHolder);
        instance.start(animationEndHandler);
        doReturn(1).when(animationAnalyzer).findFramesCount(any(Size.class), any(Size.class));
        ArgumentCaptor<ImagePreloadHandler> imagePreloadHandler = ArgumentCaptor.<ImagePreloadHandler>forClass(ImagePreloadHandler.class);
        verify(imagePreloader).preload(eq(source), imagePreloadHandler.capture());
        imagePreloadHandler.getValue().onLoad(frameSize);
        verify(animation).start(eq(instance));
        verify(animation).setRuntimeConfiguration(any(AnimationRuntimeConfig.class));

        instance.onEnd();
        verify(animationEndHandler).onEnd();
    }

    @Test
    public void startWrongImageSize() throws Exception {
        instance.init(animation, animationConfig, animationHolder);
        instance.start(animationEndHandler);
        doReturn(0).when(animationAnalyzer).findFramesCount(any(Size.class), any(Size.class));
        ArgumentCaptor<ImagePreloadHandler> imagePreloadHandler = ArgumentCaptor.<ImagePreloadHandler>forClass(ImagePreloadHandler.class);
        verify(imagePreloader).preload(eq(source), imagePreloadHandler.capture());
        imagePreloadHandler.getValue().onLoad(frameSize);
        verify(animation, never()).start(eq(instance));
        verify(animation, never()).setRuntimeConfiguration(any(AnimationRuntimeConfig.class));
        verify(animationEndHandler).onEnd();
    }

    @Test
    public void terminateBeforeImgLoaded() throws Exception {
        HandlerRegistration handlerRegistration = mock(HandlerRegistration.class);
        doReturn(handlerRegistration).when(imagePreloader).preload(anyString(), any(ImagePreloadHandler.class));
        instance.init(animation, animationConfig, animationHolder);
        instance.start(animationEndHandler);
        instance.terminate();
        verify(handlerRegistration).removeHandler();
    }

    @Test
    public void errorOnImgLoaded() throws Exception {
        instance = spy(instance);
        HandlerRegistration handlerRegistration = mock(HandlerRegistration.class);
        ArgumentCaptor<ImagePreloadHandler> imagePreloadHandler = ArgumentCaptor.<ImagePreloadHandler>forClass(ImagePreloadHandler.class);
        doReturn(handlerRegistration).when(imagePreloader).preload(anyString(), imagePreloadHandler.capture());
        instance.init(animation, animationConfig, animationHolder);
        instance.start(animationEndHandler);
        ImagePreloadHandler preloadHandler = imagePreloadHandler.getValue();
        preloadHandler.onError();
        instance.onEnd();
    }

    @Test
    public void terminate() {

        doReturn(1).when(animationAnalyzer).findFramesCount(any(Size.class), any(Size.class));
        ArgumentCaptor<ImagePreloadHandler> imagePreloadHandler = ArgumentCaptor.<ImagePreloadHandler>forClass(ImagePreloadHandler.class);
        instance.init(animation, animationConfig, animationHolder);
        instance.start(animationEndHandler);
        verify(imagePreloader).preload(eq(source), imagePreloadHandler.capture());
        imagePreloadHandler.getValue().onLoad(frameSize);
        verify(animation).start(eq(instance));
        instance.terminate();
        verify(animation).terminate();
        verify(animationEndHandler, never()).onEnd();
    }

    @Test
    public void onEndWithoutAnimationEndHandler() {
        instance = spy(instance);
        instance.onEnd();
        verify(instance).onEnd();
        verifyNoMoreInteractions(instance);
    }

    @Test
    public void setAnimationRuntimeConfig() {
        instance.init(animation, animationConfig, animationHolder);
        instance.start(animationEndHandler);
        int framesCount = 13;
        doReturn(framesCount).when(animationAnalyzer).findFramesCount(any(Size.class), any(Size.class));

        ArgumentCaptor<ImagePreloadHandler> imagePreloadHandler = ArgumentCaptor.<ImagePreloadHandler>forClass(ImagePreloadHandler.class);
        verify(imagePreloader).preload(eq(source), imagePreloadHandler.capture());
        imagePreloadHandler.getValue().onLoad(frameSize);
        verify(animation).start(eq(instance));

        ArgumentCaptor<AnimationRuntimeConfig> runtimeConfigArgumentCaptor = ArgumentCaptor.forClass(AnimationRuntimeConfig.class);
        verify(animation).setRuntimeConfiguration(runtimeConfigArgumentCaptor.capture());
        AnimationRuntimeConfig runtimeConfig = runtimeConfigArgumentCaptor.getValue();

        assertThat(runtimeConfig.getAnimationConfig()).isEqualTo(animationConfig);
        assertThat(runtimeConfig.getAnimationHolder()).isEqualTo(animationHolder);
        assertThat(runtimeConfig.getFramesCount()).isEqualTo(framesCount);
        assertThat(runtimeConfig.getImageSize()).isEqualTo(frameSize);

    }

}

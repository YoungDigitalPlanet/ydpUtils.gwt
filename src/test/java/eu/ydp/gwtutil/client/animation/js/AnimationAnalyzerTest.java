package eu.ydp.gwtutil.client.animation.js;

import eu.ydp.gwtutil.client.util.geom.Size;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class AnimationAnalyzerTest {

    private final AnimationAnalyzer analyzer = new AnimationAnalyzer();

    @Test
    public void findFramesCount_standardCase() {
        // given
        Size imageSize = new Size(100, 40);
        Size frameSize = new Size(20, 40);

        // when
        int framesCount = analyzer.findFramesCount(imageSize, frameSize);

        // then
        assertThat(framesCount).isEqualTo(5);
    }

    @Test
    public void findFramesCount_oneFrame() {
        // given
        Size imageSize = new Size(20, 40);
        Size frameSize = new Size(20, 40);

        // when
        int framesCount = analyzer.findFramesCount(imageSize, frameSize);

        // then
        assertThat(framesCount).isEqualTo(1);
    }

    @Test
    public void findFramesCount_divisionBy0() {
        // given
        Size imageSize = new Size(100, 40);
        Size frameSize = new Size(0, 40);

        // when
        int framesCount = analyzer.findFramesCount(imageSize, frameSize);

        // then
        assertThat(framesCount).isEqualTo(0);
    }

    @Test
    public void findFramesCount_negativeFrameWidth() {
        // given
        Size imageSize = new Size(100, 40);
        Size frameSize = new Size(-50, 40);

        // when
        int framesCount = analyzer.findFramesCount(imageSize, frameSize);

        // then
        assertThat(framesCount).isEqualTo(0);
    }

    @Test
    public void findFramesCount_heightDiffers() {
        // given
        Size imageSize = new Size(100, 40);
        Size frameSize = new Size(20, 4340);

        // when
        int framesCount = analyzer.findFramesCount(imageSize, frameSize);

        // then
        assertThat(framesCount).isEqualTo(5);
    }

}

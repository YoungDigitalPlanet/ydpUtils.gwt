package eu.ydp.gwtutil.client.animation;

import com.google.common.base.Objects;
import eu.ydp.gwtutil.client.util.geom.Size;

import static com.google.common.base.Objects.equal;

public class AnimationConfig {

    private static final int MS_IN_SECONDS = 1000;
    private final int fps;
    private final Size frameSize;
    private final String source;

    public AnimationConfig(int fps, Size frameSize, String source) {
        this.fps = fps;
        this.frameSize = frameSize;
        this.source = source;
    }

    public int getFps() {
        return fps;
    }

    public int getIntervalMs() {
        if (fps > 0) {
            return MS_IN_SECONDS / fps;
        } else {
            return MS_IN_SECONDS;
        }
    }

    public Size getFrameSize() {
        return frameSize;
    }

    public String getSource() {
        return source;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fps, frameSize, source);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AnimationConfig)) {
            return false;
        }
        AnimationConfig other = (AnimationConfig) obj;
        return equal(fps, other.fps) && equal(frameSize, other.frameSize) && equal(source, other.source);
    }

}

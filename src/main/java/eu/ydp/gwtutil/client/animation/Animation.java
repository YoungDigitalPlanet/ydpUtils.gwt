package eu.ydp.gwtutil.client.animation;

public interface Animation {
    void start(AnimationEndHandler handler);

    void terminate();
}

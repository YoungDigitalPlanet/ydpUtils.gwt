package eu.ydp.gwtutil.client.animation.preload;

import eu.ydp.gwtutil.client.util.geom.Size;

public interface ImagePreloadHandler {

	void onLoad(Size imageSize);

	void onError();
}

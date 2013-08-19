package eu.ydp.gwtutil.client.animation.js;

import eu.ydp.gwtutil.client.util.geom.Size;


public class AnimationAnalyzer {

	public int findFramesCount(Size imageSize, Size frameSize) {
		if (frameSize.getWidth() <= 0){
			return 0;
		}
		int imgWidth = imageSize.getWidth();
		int frameWidth = frameSize.getWidth();
		int framesCount = imgWidth / frameWidth;
		return framesCount;
	}
}

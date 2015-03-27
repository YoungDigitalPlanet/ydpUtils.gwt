package eu.ydp.gwtutil.client.util;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;

import eu.ydp.gwtutil.client.util.UserAgentChecker.RuntimeMobileUserAgent;

public class AudioWrapperImpl implements AudioWrapper {

	@Inject
	private UserAgentUtil userAgentUtil;

	@Override
	public boolean isAudioSupported() {
		return Audio.isSupported();
	}

	@Override
	public boolean isMimeSupported(String mimeType) {
		Audio audio = Audio.createIfSupported();

		if (audio == null)
			return false;

		String playableType = audio.canPlayType(mimeType);
		playableType = changeEmptyStringToCanPlayProbablyIfAndroid404(playableType);

		return isPlayable(playableType);
	}

	private boolean isPlayable(String canPlayType) {
		return !MediaElement.CANNOT_PLAY.equals(canPlayType);
	}

	private String changeEmptyStringToCanPlayProbablyIfAndroid404(String canPlayType) {
		if (userAgentUtil.isUserAgent(RuntimeMobileUserAgent.ANDROID404) && Strings.isNullOrEmpty(canPlayType)) {
			canPlayType = MediaElement.CAN_PLAY_PROBABLY;
		}
		return canPlayType;
	}
}

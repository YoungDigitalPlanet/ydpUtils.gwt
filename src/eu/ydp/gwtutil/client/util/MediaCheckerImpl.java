package eu.ydp.gwtutil.client.util;

import javax.inject.Inject;

public class MediaCheckerImpl implements MediaChecker {

	private static final String MIME_AUDIO_OGG = "audio/ogg";
	private static final String MIME_AUDIO_MP4 = "audio/mp4";

	@Inject
	private AudioWrapper audioWrapper;

	@Inject
	private UserAgentUtil userAgentUtil;

	@Override
	public boolean isHtml5Mp3Supported() {
		return isMp3AudioMimeTypeSupported() || isLocalAndroidWithAudioSupport();
	}

	private boolean isMp3AudioMimeTypeSupported() {
		return audioWrapper.isAudioSupported() && audioWrapper.isMimeSupported(MIME_AUDIO_MP4);
	}

	private boolean isLocalAndroidWithAudioSupport() {
		return audioWrapper.isAudioSupported() && userAgentUtil.isAndroidBrowser() && userAgentUtil.isLocal();
	}

	@Override
	public boolean isHtml5OggSupported() {
		return audioWrapper.isAudioSupported() && audioWrapper.isMimeSupported(MIME_AUDIO_OGG);
	}

}

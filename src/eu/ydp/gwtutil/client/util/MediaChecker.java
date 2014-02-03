package eu.ydp.gwtutil.client.util;

import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;

import eu.ydp.gwtutil.client.util.UserAgentChecker.RuntimeMobileUserAgent;

public final class MediaChecker {

	private MediaChecker() {
	}

	private static final String NO = "no";
	private static final String OGG = "audio/ogg";
	private static final String MP3 = "audio/mp3";

	public static boolean isHtml5Mp3Support() {
		return isHtml5AudioSupport(MP3);
	}

	public static boolean isHtml5OggSupport() {
		return isHtml5AudioSupport(OGG);
	}

	private static boolean isHtml5AudioSupport(String type) {
		return Audio.isSupported() && (canPlay(type) || isLocallyMP3OnAndroid(type)) && !NO.equals(Audio.createIfSupported().canPlayType(type));
	}

	/**
	 * HACK : android v.404 zwraca pusty string ale potrafi odtworzyc dzwiek
	 */
	private static boolean canPlay(String type) {
		String canPlayType = Audio.createIfSupported().canPlayType(type);
		boolean isAndroid404 = UserAgentChecker.isUserAgent(RuntimeMobileUserAgent.ANDROID404);

		return !MediaElement.CANNOT_PLAY.equals(canPlayType) || isAndroid404;
	}

	private static boolean isLocallyMP3OnAndroid(String type) {
		return UserAgentChecker.isAndroidBrowser() && UserAgentChecker.isLocal() && type.equals(MP3);
	}
}

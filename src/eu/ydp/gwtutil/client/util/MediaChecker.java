package eu.ydp.gwtutil.client.util;

import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;

public final class MediaChecker {

	private MediaChecker(){}
	
	private static final String NO = "no";
	private static final String OGG = "audio/ogg";
	private static final String MP3 = "audio/mp3";
	
	public static boolean isHtml5Mp3Support(){
		return isHtml5AudioSupport(MP3);
	}
	
	public static boolean isHtml5OggSupport(){
		return isHtml5AudioSupport(OGG);
	}
	
	private static boolean isHtml5AudioSupport(String type){
		return Audio.isSupported()  &&  !MediaElement.CANNOT_PLAY.equals(Audio.createIfSupported().canPlayType(type))  &&  !NO.equals(Audio.createIfSupported().canPlayType(type));
	}

}

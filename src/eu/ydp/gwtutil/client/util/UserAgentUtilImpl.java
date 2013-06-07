package eu.ydp.gwtutil.client.util;

import com.google.inject.Inject;

import eu.ydp.gwtutil.client.debug.gwtlogger.Logger;
import eu.ydp.gwtutil.client.util.UserAgentChecker.BrowserUserAgent;
import eu.ydp.gwtutil.client.util.UserAgentChecker.MobileUserAgent;

public class UserAgentUtilImpl implements UserAgentUtil {
	
	private static final Logger LOGGER = new Logger();

	@Inject
	protected BrowserNativeInterface nativeInterface;

	protected static Boolean isStackAndroidBrowser = null;
	protected static MobileUserAgent mobileUserAgent = null;

	protected static final MobileUserAgent[] ANDROID_USER_AGENTS = new MobileUserAgent[] {
		    MobileUserAgent.ANDROID23, MobileUserAgent.ANDROID3,
			MobileUserAgent.ANDROID321, MobileUserAgent.ANDROID4,
			MobileUserAgent.ANDROID_OTHER };

	public void setNativeInterface(BrowserNativeInterface nativeInterface) {
		this.nativeInterface = nativeInterface;
		mobileUserAgent = null;
	}

	@Override
	public boolean isMobileUserAgent(MobileUserAgent userAgent) {
		return isUserAgent(userAgent);
	}

	@Override
	public boolean isMobileUserAgent(MobileUserAgent... userAgent) {
		return isUserAgent(userAgent);
	}

	@Override
	public boolean isMobileUserAgent() {
		return getMobileUserAgent() != MobileUserAgent.UNKNOWN;
	}

	@Override
	public boolean isStackAndroidBrowser() {
		if (isStackAndroidBrowser == null) {
			isStackAndroidBrowser = isMobileUserAgent(ANDROID_USER_AGENTS) && !isMobileUserAgent(MobileUserAgent.CHROME, MobileUserAgent.FIREFOX);
		}

		return isStackAndroidBrowser;
	}

	@Override
	public boolean isUserAgent(BrowserUserAgent userAgent) {
		return nativeInterface.isUserAgent(userAgent.getRegexPattern(), getUserAgentStrting());

	}

	@Override
	public boolean isUserAgent(BrowserUserAgent... userAgents) {
		for (BrowserUserAgent uAgent : userAgents) {
			if (nativeInterface.isUserAgent(uAgent.getRegexPattern(), getUserAgentStrting())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getUserAgentStrting() {
		return nativeInterface.getUserAgentStrting().toLowerCase();
	}

	@Override
	public MobileUserAgent getMobileUserAgent() {
		if (mobileUserAgent == null) {
			mobileUserAgent = MobileUserAgent.UNKNOWN;
			for (MobileUserAgent ua : MobileUserAgent.values()) {
				if (isMobileUserAgent(ua)) {
					mobileUserAgent = ua;
					break;
				}
			}
		}
		return mobileUserAgent;
	}

	@Override
	public boolean isLocal() {
		return nativeInterface.isLocal();
	}

	
	/*
	 * It's not proven that this method is working correctly on all browsers. If specific browser is not listed below as tested, it's recommended to test it before use.
	 * 
	 */
	@Override
	public boolean isInsideIframe(){
		boolean isInsideIframe = !isCurrentWindowTop();
		
		LOGGER.info("UserAgentUtil.isInsideIframe - returned "+isInsideIframe);
		return isInsideIframe;
	}
	
	/*
	 * It's not proven that this method is working correctly on all browsers. If specific browser is not listed below as tested, it's recommended to test it before use.
	 * 
	 */
	public native boolean isCurrentWindowTop() /*-{
		if(top === self){
			return true;
		}else{
			return false;
		}
	}-*/;
	
	@Override
	public native boolean isAIR() /*-{
		return $wnd.navigator.isAIR;
	}-*/;

	@Override
	public boolean isIE() {
		return nativeInterface.isUserAgent(".*msie[ ]*[0-9]{1,2}.*trident.*", getUserAgentStrting());
	}

}

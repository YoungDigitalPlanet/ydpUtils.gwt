package eu.ydp.gwtutil.client.util;

import eu.ydp.gwtutil.user.rebind.MobileUserAgentPropertyGenerator;

/**
 * Klasa pomocnicza do sprawdzania userAgenta
 *
 */
public class UserAgentChecker {
	protected static BrowserNativeInterface nativeInterface = new BrowserNativeInterfaceImpl();
	protected static MobileUserAgent mobileUserAgent = null;
	public static final MobileUserAgent[] ANDROID_USER_AGENTS = new MobileUserAgent[] { MobileUserAgent.ANDROID23, MobileUserAgent.ANDROID3,
			MobileUserAgent.ANDROID321, MobileUserAgent.ANDROID4 };

	public interface BrowserUserAgent {
		public String getRegexPattern();
	}

	/**
	 * Konfiguracja mobilnego user agenta. Wartosci musza byc zsynchronizowane z
	 * module.gwt.xml oraz {@link MobileUserAgentPropertyGenerator}
	 *
	 */
	public enum MobileUserAgent implements BrowserUserAgent {
		AIR("air", ".*adobeair.*"),
		CHROME("chrome", "mozilla.*android.*chrome\\/[1-9]{1}[0-9]{1}.*"),
		FIREFOX("firefox","mozilla.*android.*firefox\\/[1-9]{1}[0-9]{1}.*"),
		SAFARI("safari", ".*(ipad|ipod|iphon).*applewebkit.*safari.*"),
		ANDROID23("android23","android[ ]*2.3[.0-9a-z -]*"),
		ANDROID321("android321", "android[ ]*3.2.1[.0-9a-z -]*"),
		ANDROID3("android3", "android[ ]*3[.0-9a-z -]*"),
		ANDROID4("android4", "android[ ]*4[.0-9a-z -]*"),
		UNKNOWN("unknown", ".*");
		private final String tagName, regexPattern;

		private MobileUserAgent(String name, String regex) {
			this.tagName = name;
			this.regexPattern = regex;
		}

		/**
		 * regex wykonywany przy sprawdzaniu useragenta. kod wykonywany natywnie
		 *
		 * @return
		 */
		@Override
		public String getRegexPattern() {
			return regexPattern;
		}

		/**
		 * nazwa useragenta
		 */
		public String tagName() {
			return tagName;
		}
	}

	/**
	 * W przyszlosci do zsynchronizwania z UserAgent dostarczanym przez gwt
	 *
	 */
	public enum UserAgent implements BrowserUserAgent{
		GECKO1_8("gecko1_8", "^(((?!.*like).*)(.*gecko.*))$"),
		OPERA("opera", ".*opera.*"),
		IE9("ie9", ".*msie[ ]*9.*"),
		IE8("ie8",".*msie[ ]*[78]{1}.*trident\\/4.*"),
		SAFARI("safari", ".*webkit.*"),
		ALL("all", ".*");
		private final String tagName, regexPattern;

		private UserAgent(String name, String regex) {
			this.tagName = name;
			this.regexPattern = regex;
		}

		/**
		 * regex wykonywany przy sprawdzaniu useragenta. kod wykonywany natywnie
		 *
		 * @return
		 */
		@Override
		public String getRegexPattern() {
			return regexPattern;
		}

		/**
		 * nazwa useragenta
		 */
		public String tagName() {
			return tagName;
		}
	}

	/**
	 * Sprawdza czy podany parametr odpowiada userAgent w przegladarce
	 *
	 * @param userAgent
	 * @return
	 */
	public static boolean isMobileUserAgent(MobileUserAgent userAgent) {
		return isUserAgent(userAgent);
	}

	/**
	 * Sprawdza czy jeden z elementow kolekcji odpowiada userAgent w
	 * przegladarce
	 *
	 * @param userAgent
	 * @return
	 */
	public static boolean isMobileUserAgent(MobileUserAgent... userAgent) {
		return isUserAgent(userAgent);
	}

	/**
	 * Czy przegladarka jest przegladarka uruhomiona na urzadzeniu mobilnym
	 *
	 * @return
	 */
	public static boolean isMobileUserAgent() {
		return getMobileUserAgent() != MobileUserAgent.UNKNOWN;
	}

	public static boolean isStackAndroidBrowser() {
		if (isMobileUserAgent(ANDROID_USER_AGENTS) && !isMobileUserAgent(MobileUserAgent.CHROME, MobileUserAgent.FIREFOX)) {
			return true;
		}
		return false;
	}

	public static boolean isUserAgent(BrowserUserAgent userAgent) {
		return nativeInterface.isUserAgent(userAgent.getRegexPattern(), UserAgentChecker.getUserAgentStrting());
	}

	public static boolean isUserAgent(BrowserUserAgent... userAgents) {
		for (BrowserUserAgent uAgent : userAgents) {
			if (nativeInterface.isUserAgent(uAgent.getRegexPattern(), UserAgentChecker.getUserAgentStrting())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * zwraca navigator.userAgent
	 *
	 * @return
	 */
	public static String getUserAgentStrting() {
		return nativeInterface.getUserAgentStrting().toLowerCase();
	}

	/**
	 * zwraca userAgenta przegladarki
	 */
	public static MobileUserAgent getMobileUserAgent() {
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
	
	public static boolean isBrowserSupportingMp3(){
		return !isUserAgent(UserAgent.GECKO1_8, UserAgent.OPERA, MobileUserAgent.FIREFOX);
	}

	/**
	 * Czy aplikacja jest uruchomiona loklanie
	 *
	 * @return
	 */
	public static boolean isLocal() {
		return nativeInterface.isLocal();
	}

	public static void setNativeInterface(BrowserNativeInterface nativeInterface) {
		UserAgentChecker.nativeInterface = nativeInterface;
	}

}

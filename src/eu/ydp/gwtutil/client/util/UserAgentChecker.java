package eu.ydp.gwtutil.client.util;

import eu.ydp.gwtutil.user.rebind.MobileUserAgentPropertyGenerator;

/**
 * Klasa pomocnicza do sprawdzania userAgenta
 *
 */
public class UserAgentChecker {
	protected static UserAgentUtilImpl userAgentUtilImpl = new UserAgentUtilImpl();
	static{
		userAgentUtilImpl.setNativeInterface(new BrowserNativeInterfaceImpl());
	}

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
		SAFARI_WEBVIEW("safari_webview", ".*(ipad|ipod|iphon).*applewebkit(?!(.*safari.*)).*"),
		ANDROID23("android23","android[ ]*2.3[.0-9a-z -]*"),
		ANDROID321("android321", "android[ ]*3.2.1[.0-9a-z -]*"),
		ANDROID3("android3", "android[ ]*3[.0-9a-z -]*"),
		ANDROID4("android4", "android[ ]*4[.0-9a-z -]*"),
		ANDROID_OTHER("android_other", "android[ ]*[5-9][.0-9a-z -]*"),  // watch this regex when adding rule for subsequent Android version
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

	public enum RuntimeMobileUserAgent implements BrowserUserAgent {
		IOS6("ios6", ".*(ipad|ipod|iphon).*os[ ]+6_0.*"),
		IOS6_1("ios6_1", ".*(ipad|ipod|iphon).*os[ ]+6_1.*"),
		ANDROID404("android404", ".*android 4.0.4");

		private final String tagName, regexPattern;
		private RuntimeMobileUserAgent(String name, String regex) {
			this.tagName = name;
			this.regexPattern = regex;
		}

		@Override
		public String getRegexPattern() {
			return regexPattern;
		}

		public String getTagName() {
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
		return userAgentUtilImpl.isMobileUserAgent();
	}

	public static boolean isStackAndroidBrowser() {
		return userAgentUtilImpl.isStackAndroidBrowser();
	}

	public static boolean isUserAgent(BrowserUserAgent userAgent) {
		return userAgentUtilImpl.isUserAgent(userAgent);
	}

	public static boolean isUserAgent(BrowserUserAgent... userAgents) {
		return userAgentUtilImpl.isUserAgent(userAgents);
	}

	/**
	 * zwraca navigator.userAgent
	 *
	 * @return
	 */
	public static String getUserAgentStrting() {
		return userAgentUtilImpl.getUserAgentStrting();
	}

	/**
	 * zwraca userAgenta przegladarki
	 */
	public static MobileUserAgent getMobileUserAgent() {
		return userAgentUtilImpl.getMobileUserAgent();
	}

	/**
	 * Czy aplikacja jest uruchomiona loklanie
	 *
	 * @return
	 */
	public static boolean isLocal() {
		return userAgentUtilImpl.isLocal();
	}

	public static void setNativeInterface(BrowserNativeInterface nativeInterface) {
		userAgentUtilImpl.setNativeInterface(nativeInterface);
	}

	/**
	 * Aby to zadzialalo w pliku HTML w ktorym osadzona jest Empiria nalezy dodac kod javascriptowy
	 * <code>navigator.isAIR = true;</code>.
	 */
	public static boolean isAIR() {
		return userAgentUtilImpl.isAIR();
	}

}

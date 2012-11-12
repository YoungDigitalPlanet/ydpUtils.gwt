package eu.ydp.gwtutil.useragent;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.ydp.gwtutil.client.util.BrowserNativeInterface;
import eu.ydp.gwtutil.client.util.UserAgentChecker;
import eu.ydp.gwtutil.client.util.UserAgentChecker.MobileUserAgent;
import eu.ydp.gwtutil.client.util.UserAgentChecker.UserAgent;

public class UserAgentCheckerJUnitTest {

	private Map<String, UserAgent> getDesktop() {
		Map<String, UserAgent> browsers = new HashMap<String, UserAgent>();
		browsers.put("Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2", UserAgent.GECKO1_8);
		browsers.put("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:14.0) Gecko/20100101 Firefox/14.0.1", UserAgent.GECKO1_8);
		browsers.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; de) Opera 11.51", UserAgent.OPERA);
		browsers.put("Opera/9.80 (X11; Linux i686; U; hu) Presto/2.9.168 Version/11.50", UserAgent.OPERA);
		browsers.put("Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))", UserAgent.IE9);
		browsers.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)",
				UserAgent.IE9);
		return browsers;
	}

	private Map<String, MobileUserAgent> getMobile() {
		Map<String, MobileUserAgent> browsers = new HashMap<String, MobileUserAgent>();
		browsers.put("Mozilla/5.0 (Android; Tablet; rv:13.0) Gecko/13.0 Firefox/13.0", MobileUserAgent.FIREFOX);
		browsers.put("Mozilla/5.0 (Android; Mobile; rv:10.0.5) Gecko/10.0.5 Firefox/10.0.5 Fennec/10.0.5", MobileUserAgent.FIREFOX);
		browsers.put(
				"Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
				MobileUserAgent.ANDROID4);
		browsers.put(
				"Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
				MobileUserAgent.ANDROID4);
		browsers.put("Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
				MobileUserAgent.ANDROID3);
		browsers.put("Mozilla/5.0 (Linux; U; Android 3.2; en-gb; A501 Build/HTK55D) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
				MobileUserAgent.ANDROID3);
		browsers.put("Mozilla/5.0 (Linux; U; Android 3.0; en-us; A500 Build/HRI66) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
				MobileUserAgent.ANDROID3);
		browsers.put("Mozilla/5.0 (Linux; U; Android 2.3; en-us) AppleWebKit/999+ (KHTML, like Gecko) Safari/999.9", MobileUserAgent.ANDROID23);
		browsers.put(
				"Mozilla/5.0 (Linux; U; Android 2.3.5; en-us; HTC Vision Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
				MobileUserAgent.ANDROID23);
		browsers.put(
				"Mozilla/5.0 (Linux; U; Android 2.3.3; zh-tw; HTC_Pyramid Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
				MobileUserAgent.ANDROID23);
		browsers.put("Mozilla/5.0 (Linux; U; Android 3.2.1; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
				MobileUserAgent.ANDROID321);
		browsers.put("Mozilla/5.0 (Linux; U; Android 3.2.1; en-gb; A501 Build/HTK55D) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
				MobileUserAgent.ANDROID321);
		browsers.put("Mozilla/5.0 (Linux; U; Android 3.2.1; en-us; A500 Build/HRI66) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
				MobileUserAgent.ANDROID321);
		browsers.put("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25",
				MobileUserAgent.SAFARI);
		browsers.put(
				"Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; ja-jp) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
				MobileUserAgent.SAFARI);
		browsers.put("Mozilla/5.0 (Windows; U; en-US) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1", MobileUserAgent.AIR);
		browsers.put("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1", MobileUserAgent.AIR);

		return browsers;
	}

	private BrowserNativeInterface fillMock(String userAgent) {
		BrowserNativeInterface nativeInterface = Mockito.mock(BrowserNativeInterface.class);
		Mockito.when(nativeInterface.getUserAgentStrting()).thenReturn(userAgent);
		Mockito.when(nativeInterface.isLocal()).thenReturn(false);
		Mockito.when(nativeInterface.isUserAgent(Mockito.any(String.class), Mockito.any(String.class))).then(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Pattern pattern = Pattern.compile(String.valueOf(args[0]));
				return pattern.matcher(String.valueOf(args[1])).find();
			}
		});
		return nativeInterface;
	}

	@Test
	public void userAgentDesktopTest() {
		for (Map.Entry<String, UserAgent> ua : getDesktop().entrySet()) {
			BrowserNativeInterface nativeInterface = fillMock(ua.getKey());
			UserAgentChecker.setNativeInterface(nativeInterface);
			Assert.assertTrue(UserAgentChecker.isUserAgent(new UserAgent[] { ua.getValue() }));

		}
	}

	@Test
	public void userAgentMobileTest() {
		for (Map.Entry<String, MobileUserAgent> ua : getMobile().entrySet()) {
			BrowserNativeInterface nativeInterface = fillMock(ua.getKey());
			UserAgentChecker.setNativeInterface(nativeInterface);
			Assert.assertTrue(ua.getKey(), UserAgentChecker.isMobileUserAgent(ua.getValue()));
			Assert.assertTrue(ua.getKey(), UserAgentChecker.isMobileUserAgent());
		}
	}

	@Test
	public void stackAndroidBrowserUserAgentTest() {
		Map<String, MobileUserAgent> androidUserAgents = getMobile();
		for (MobileUserAgent androidUserAgent : UserAgentChecker.ANDROID_USER_AGENTS) {
			for (Map.Entry<String, MobileUserAgent> ua : androidUserAgents.entrySet()) {
				if (ua.getValue() == androidUserAgent) {
					BrowserNativeInterface nativeInterface = fillMock(ua.getKey());
					UserAgentChecker.setNativeInterface(nativeInterface);
					Assert.assertTrue(ua.getKey(), UserAgentChecker.isMobileUserAgent(ua.getValue()));
					Assert.assertTrue(ua.getKey(), UserAgentChecker.isMobileUserAgent());
					Assert.assertTrue(ua.getKey(), UserAgentChecker.isStackAndroidBrowser());
				}
			}
		}
	}
}

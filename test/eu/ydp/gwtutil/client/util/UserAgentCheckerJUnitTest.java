package eu.ydp.gwtutil.client.util;

import static junitparams.JUnitParamsRunner.*;
import static org.junit.Assert.*;

import java.util.Set;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Sets;

import eu.ydp.gwtutil.client.util.UserAgentChecker.MobileUserAgent;
import eu.ydp.gwtutil.client.util.UserAgentChecker.UserAgent;
import eu.ydp.gwtutil.junit.mock.UserAgentCheckerNativeInterfaceMock;

@RunWith(JUnitParamsRunner.class)
public class UserAgentCheckerJUnitTest {

	@SuppressWarnings("unused")
	private Object[] getDesktop() {
		// @formatter:off
		return $(
				$("Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2", 
						UserAgent.GECKO1_8),
				$("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:14.0) Gecko/20100101 Firefox/14.0.1", 
						UserAgent.GECKO1_8),
				$("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; de) Opera 11.51", 
						UserAgent.OPERA),
				$("Opera/9.80 (X11; Linux i686; U; hu) Presto/2.9.168 Version/11.50", 
						UserAgent.OPERA),
				$("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.3; Creative AutoUpdate v1.40.02)",
						UserAgent.IE8),
				$("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0)",
						UserAgent.IE8),
				$("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)",
						UserAgent.IE9), 
				$("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)", 
						UserAgent.IE10),
				$("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36", 
						UserAgent.CHROME));
		// @formatter:on
	}

	@SuppressWarnings("unused")
	private Object[] getMobile() {
		// @formatter:off
		return $(
				$("Mozilla/5.0 (Android; Tablet; rv:13.0) Gecko/13.0 Firefox/13.0", 
						MobileUserAgent.FIREFOX),
				$("Mozilla/5.0 (Android; Mobile; rv:10.0.5) Gecko/10.0.5 Firefox/10.0.5 Fennec/10.0.5", 
						MobileUserAgent.FIREFOX),
				$("Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
						MobileUserAgent.ANDROID4),
				$("Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
						MobileUserAgent.ANDROID4),
				$("Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
						MobileUserAgent.ANDROID3),
				$("Mozilla/5.0 (Linux; U; Android 3.2; en-gb; A501 Build/HTK55D) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
						MobileUserAgent.ANDROID3),
				$("Mozilla/5.0 (Linux; U; Android 3.0; en-us; A500 Build/HRI66) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
						MobileUserAgent.ANDROID3),
				$("Mozilla/5.0 (Linux; U; Android 2.3; en-us) AppleWebKit/999+ (KHTML, like Gecko) Safari/999.9", 
						MobileUserAgent.ANDROID23),
				$("Mozilla/5.0 (Linux; U; Android 2.3.5; en-us; HTC Vision Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
						MobileUserAgent.ANDROID23),
				$("Mozilla/5.0 (Linux; U; Android 2.3.3; zh-tw; HTC_Pyramid Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
						MobileUserAgent.ANDROID23),
				$("Mozilla/5.0 (Linux; U; Android 3.2.1; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
						MobileUserAgent.ANDROID321),
				$("Mozilla/5.0 (Linux; U; Android 3.2.1; en-gb; A501 Build/HTK55D) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
						MobileUserAgent.ANDROID321),
				$("Mozilla/5.0 (Linux; U; Android 3.2.1; en-us; A500 Build/HRI66) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
						MobileUserAgent.ANDROID321),
				$("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25",
						MobileUserAgent.SAFARI),
				$("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; ja-jp) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
						MobileUserAgent.SAFARI),
				$("Mozilla/5.0 (Windows; U; en-US) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1",
						MobileUserAgent.AIR),
				$("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1",
						MobileUserAgent.AIR),
				$("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.34 Safari/534.24",
						MobileUserAgent.ANDROID_DESKTOP_MODE));
		// @formatter:on

	}

	@Test
	@Parameters(method = "getDesktop")
	public void userAgentDesktopTest(String userAgentString, UserAgent userAgent) {
		BrowserNativeInterface nativeInterface = UserAgentCheckerNativeInterfaceMock.getNativeInterfaceMock(userAgentString);
		UserAgentChecker.setNativeInterface(nativeInterface);

		assertTrue(UserAgentChecker.isUserAgent(userAgent));
	}

	@Test
	@Parameters(method = "getMobile")
	public void userAgentMobileTest(String userAgentString, MobileUserAgent userAgent) {
		BrowserNativeInterface nativeInterface = UserAgentCheckerNativeInterfaceMock.getNativeInterfaceMock(userAgentString);
		UserAgentChecker.setNativeInterface(nativeInterface);
		Assert.assertTrue(userAgentString, UserAgentChecker.isMobileUserAgent(userAgent));
		Assert.assertTrue(userAgentString, UserAgentChecker.isMobileUserAgent(userAgent));
	}

	@Test
	@Parameters(method = "getDesktop")
	public void userAgentIE(String userAgentString, UserAgent userAgent) {
		Set<UserAgent> ieAgents = Sets.newHashSet(UserAgent.IE8, UserAgent.IE9, UserAgent.IE10);

		BrowserNativeInterface nativeInterface = UserAgentCheckerNativeInterfaceMock.getNativeInterfaceMock(userAgentString);
		UserAgentChecker.setNativeInterface(nativeInterface);

		assertEquals(ieAgents.contains(userAgent), UserAgentChecker.isIE());
	}

}

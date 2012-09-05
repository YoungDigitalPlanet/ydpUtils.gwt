package eu.ydp.gwtutil.useragent;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import eu.ydp.gwtutil.client.util.UserAgentChecker.UserAgent;

public class UserAgentDesktopCheckerJUnitTest {

	private Set<String> getFirefoxDesktop() {
		Set<String> browsers = new HashSet<String>();
		browsers.add("Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2");
		browsers.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:15.0) Gecko/20120427 Firefox/15.0a1");
		browsers.add("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		browsers.add("Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko Firefox/11.0");
		browsers.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0a2) Gecko/20110612 Firefox/6.0a2");
		return browsers;
	}

	private Set<String> getOperaDesktop() {
		Set<String> browsers = new HashSet<String>();
		browsers.add("Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00");
		browsers.add("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; de) Presto/2.9.168 Version/11.52");
		browsers.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; de) Opera 11.51");
		browsers.add("Opera/9.80 (X11; Linux i686; U; hu) Presto/2.9.168 Version/11.50");
		browsers.add("Opera/9.80 (X11; Linux x86_64; U; Ubuntu/10.10 (maverick); pl) Presto/2.7.62 Version/11.01");
		return browsers;
	}

	private Set<String> getIe9Desktop() {
		Set<String> browsers = new HashSet<String>();
		browsers.add("Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))");
		browsers.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)");
		browsers.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
		browsers.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; FDM; MSIECrawler; Media Center PC 5.0)");
		browsers.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET CLR 1.1.4322; .NET4.0C; Tablet PC 2.0)");
		return browsers;
	}

	@Test
	public void firefoxDesktop() {
		Set<String> browsers = getFirefoxDesktop();
		for (String ua : browsers) {
			Assert.assertTrue(ua.toLowerCase().matches(UserAgent.GECKO1_8.getRegexPattern()));
		}

		browsers = getOperaDesktop();
		browsers.addAll(getIe9Desktop());
		for (String ua : browsers) {
			Assert.assertFalse(ua.toLowerCase().matches(UserAgent.GECKO1_8.getRegexPattern()));
		}

	}

	@Test
	public void operaDesktop() {
		Set<String> browsers = getOperaDesktop();
		for (String ua : browsers) {
			Assert.assertTrue(ua.toLowerCase().matches(UserAgent.OPERA.getRegexPattern()));
		}

		browsers = getIe9Desktop();
		for (String ua : browsers) {
			Assert.assertFalse(ua.toLowerCase().matches(UserAgent.OPERA.getRegexPattern()));
		}
	}

	@Test
	public void ie9Desktop() {
		Set<String> browsers = getIe9Desktop();
		for (String ua : browsers) {
			Assert.assertTrue(ua.toLowerCase().matches(UserAgent.IE9.getRegexPattern()));
		}

		browsers = getFirefoxDesktop();
		for (String ua : browsers) {
			Assert.assertFalse(ua.toLowerCase().matches(UserAgent.IE9.getRegexPattern()));
		}
	}

	@Test
	public void allDesktop() {
		Set<String> browsers = getIe9Desktop();
		browsers.addAll(getFirefoxDesktop());
		browsers.addAll(getOperaDesktop());
		for (String ua : browsers) {
			Assert.assertTrue(ua.toLowerCase().matches(UserAgent.ALL.getRegexPattern()));
		}
	}
}

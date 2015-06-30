package eu.ydp.gwtutil.useragent;

import eu.ydp.gwtutil.client.util.UserAgentChecker.MobileUserAgent;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentMobileCheckerJUnitTest {
    private Set<String> getFirefoxMobile() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/13.0");
        browsers.add("Mozilla/5.0 (Android; Tablet; rv:13.0) Gecko/13.0 Firefox/13.0");
        browsers.add("Mozilla/5.0 (Android; Mobile; rv:10.0.5) Gecko/10.0.5 Firefox/10.0.5 Fennec/10.0.5");
        browsers.add("Mozilla/5.0 (Android; Tablet; rv:10.0.5) Gecko/10.0.5 Firefox/10.0.5 Fennec/10.0.5");
        return browsers;
    }

    private Set<String> getAndroid4() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
        browsers.add("Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
        return browsers;
    }

    private Set<String> getAndroid3() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
        browsers.add("Mozilla/5.0 (Linux; U; Android 3.2; en-gb; A501 Build/HTK55D) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
        browsers.add("Mozilla/5.0 (Linux; U; Android 3.0; en-us; A500 Build/HRI66) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
        return browsers;
    }

    private Set<String> getAndroid23() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Linux; U; Android 2.3; en-us) AppleWebKit/999+ (KHTML, like Gecko) Safari/999.9");
        browsers.add("Mozilla/5.0 (Linux; U; Android 2.3.5; en-us; HTC Vision Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        browsers.add("Mozilla/5.0 (Linux; U; Android 2.3.3; zh-tw; HTC_Pyramid Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        return browsers;
    }

    private Set<String> getAndroid321() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Linux; U; Android 3.2.1; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
        browsers.add("Mozilla/5.0 (Linux; U; Android 3.2.1; en-gb; A501 Build/HTK55D) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
        browsers.add("Mozilla/5.0 (Linux; U; Android 3.2.1; en-us; A500 Build/HRI66) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
        return browsers;
    }

    private Set<String> getSafariMobile() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25");
        browsers.add("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; ja-jp) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
        return browsers;
    }

    private Set<String> getAir() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Windows; U; en-US) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1");
        browsers.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1");
        browsers.add("Mozilla/5.0 (Windows; U; es-ES) AppleWebKit/531.9 (KHTML, like Gecko) AdobeAIR/2.5.1");
        return browsers;
    }

    private Set<String> getChrome() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19");
        return browsers;
    }

    @Test
    public void firefox() {
        Set<String> browsers = getFirefoxMobile();
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(MobileUserAgent.FIREFOX.getRegexPattern()));
        }
        browsers = getChrome();
        browsers.addAll(getAir());
        browsers.addAll(getSafariMobile());
        for (String ua : browsers) {
            Assert.assertFalse(ua, ua.toLowerCase().matches(MobileUserAgent.FIREFOX.getRegexPattern()));
        }
    }

    @Test
    public void chrome() {
        Set<String> browsers = getChrome();
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(MobileUserAgent.CHROME.getRegexPattern()));
        }

        browsers = getFirefoxMobile();
        browsers.addAll(getAir());
        browsers.addAll(getSafariMobile());
        for (String ua : browsers) {
            Assert.assertFalse(ua, ua.toLowerCase().matches(MobileUserAgent.CHROME.getRegexPattern()));
        }

    }

    @Test
    public void air() {
        Set<String> browsers = getAir();
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(MobileUserAgent.AIR.getRegexPattern()));
        }
        browsers = getFirefoxMobile();
        browsers.addAll(getFirefoxMobile());
        browsers.addAll(getSafariMobile());
        for (String ua : browsers) {
            Assert.assertFalse(ua, ua.toLowerCase().matches(MobileUserAgent.AIR.getRegexPattern()));
        }
    }

    @Test
    public void safari() {
        Set<String> browsers = getSafariMobile();
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(MobileUserAgent.SAFARI.getRegexPattern()));
        }
        browsers = getFirefoxMobile();
        browsers.addAll(getAir());
        browsers.addAll(getChrome());
        for (String ua : browsers) {
            Assert.assertFalse(ua, ua.toLowerCase().matches(MobileUserAgent.SAFARI.getRegexPattern()));
        }
    }

    @Test
    public void android23() {
        Set<String> browsers = getAndroid23();
        Pattern pattern = Pattern.compile(MobileUserAgent.ANDROID23.getRegexPattern());

        for (String ua : browsers) {
            Matcher matcher = pattern.matcher(ua.toLowerCase());
            Assert.assertTrue(matcher.find());
        }
    }

    @Test
    public void android321() {
        Set<String> browsers = getAndroid321();
        Pattern pattern = Pattern.compile(MobileUserAgent.ANDROID321.getRegexPattern());

        for (String ua : browsers) {
            Matcher matcher = pattern.matcher(ua.toLowerCase());
            Assert.assertTrue(matcher.find());
        }
    }

    @Test
    public void android3() {
        Set<String> browsers = getAndroid3();
        Pattern pattern = Pattern.compile(MobileUserAgent.ANDROID3.getRegexPattern());

        for (String ua : browsers) {
            Matcher matcher = pattern.matcher(ua.toLowerCase());
            Assert.assertTrue(matcher.find());
        }
    }

    @Test
    public void android4() {
        Set<String> browsers = getAndroid4();
        Pattern pattern = Pattern.compile(MobileUserAgent.ANDROID4.getRegexPattern());

        for (String ua : browsers) {
            Matcher matcher = pattern.matcher(ua.toLowerCase());
            Assert.assertTrue(matcher.find());
        }
    }

    @Test
    public void unknown() {
        Set<String> browsers = getAir();
        browsers.addAll(getAndroid23());
        browsers.addAll(getAndroid3());
        browsers.addAll(getAndroid321());
        browsers.addAll(getAndroid4());
        browsers.addAll(getFirefoxMobile());
        browsers.addAll(getSafariMobile());
        browsers.addAll(getChrome());
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(MobileUserAgent.UNKNOWN.getRegexPattern()));
        }

    }
}

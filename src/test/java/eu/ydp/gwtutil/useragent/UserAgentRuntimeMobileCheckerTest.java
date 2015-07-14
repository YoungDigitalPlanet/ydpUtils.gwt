package eu.ydp.gwtutil.useragent;

import eu.ydp.gwtutil.client.util.UserAgentChecker.RuntimeMobileUserAgent;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UserAgentRuntimeMobileCheckerTest {
    private Set<String> getIos6Mobile() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("mozilla/5.0 (iphone; cpu iphone os 6_0 like mac os x) applewebkit/536.26 (khtml, like gecko) version/6.0 mobile/10a5376e safari/8536.25");
        browsers.add("mozilla/5.0 (ipad; cpu os 6_0 like mac os x) applewebkit/536.26 (khtml, like gecko) version/6.0 mobile/10a5376e safari/8536.25");
        return browsers;
    }

    private Set<String> getIos6_1Mobile() {
        Set<String> browsers = new HashSet<String>();
        browsers.add("mozilla/5.0 (iphone; cpu iphone os 6_1 like mac os x) applewebkit/536.26 (khtml, like gecko) version/6.0 mobile/10a5376e safari/8536.25");
        browsers.add("mozilla/5.0 (ipad; cpu os 6_1 like mac os x) applewebkit/536.26 (khtml, like gecko) version/6.0 mobile/10a5376e safari/8536.25");
        return browsers;
    }

    @Test
    public void ios6UserAgentTest() {
        Set<String> browsers = getIos6Mobile();
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(RuntimeMobileUserAgent.IOS6.getRegexPattern()));
        }
        browsers = getIos6_1Mobile();
        for (String ua : browsers) {
            Assert.assertFalse(ua, ua.toLowerCase().matches(RuntimeMobileUserAgent.IOS6.getRegexPattern()));
        }
    }

    @Test
    public void ios6_1UserAgentTest() {
        Set<String> browsers = getIos6_1Mobile();
        for (String ua : browsers) {
            Assert.assertTrue(ua, ua.toLowerCase().matches(RuntimeMobileUserAgent.IOS6_1.getRegexPattern()));
        }
        browsers = getIos6Mobile();
        for (String ua : browsers) {
            Assert.assertFalse(ua, ua.toLowerCase().matches(RuntimeMobileUserAgent.IOS6_1.getRegexPattern()));
        }

    }

}

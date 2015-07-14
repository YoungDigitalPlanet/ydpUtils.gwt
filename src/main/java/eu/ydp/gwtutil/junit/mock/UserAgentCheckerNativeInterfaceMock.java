package eu.ydp.gwtutil.junit.mock;

import eu.ydp.gwtutil.client.util.BrowserNativeInterface;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.regex.Pattern;

public class UserAgentCheckerNativeInterfaceMock {
    public static final String FIREFOX_WINDOWS = "Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2";
    public static final String FIREFOX_ANDROID = "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
    public static final String IE_9 = "Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; Trident; en-US))";
    public static final String SAFARI = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";

    public static BrowserNativeInterface getNativeInterfaceMock(String userAgent) {
        BrowserNativeInterface nativeInterface = Mockito.mock(BrowserNativeInterface.class);
        Mockito.when(nativeInterface.getUserAgentStrting()).thenReturn(userAgent);
        Mockito.when(nativeInterface.isLocal()).thenReturn(false);
        Mockito.when(nativeInterface.isUserAgent(Matchers.any(String.class), Matchers.any(String.class))).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Pattern pattern = Pattern.compile(String.valueOf(args[0]));
                return pattern.matcher(String.valueOf(args[1])).find();
            }
        });
        return nativeInterface;
    }
}

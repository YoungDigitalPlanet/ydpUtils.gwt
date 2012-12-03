package eu.ydp.gwtutil.junit.mock;

import java.util.regex.Pattern;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.ydp.gwtutil.client.util.BrowserNativeInterface;

public class UserAgentCheckerNativeInterfaceMock {
	public static final String FIREFOX_UA = "Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2";
	public static final String FIREFOX_MOBILE_UA = "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
	public static final String IE ="Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))";
	public static BrowserNativeInterface getNativeInterfaceMock(String userAgent) {
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
}

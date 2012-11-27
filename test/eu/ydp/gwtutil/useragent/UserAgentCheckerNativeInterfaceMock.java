package eu.ydp.gwtutil.useragent;

import java.util.regex.Pattern;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.ydp.gwtutil.client.util.BrowserNativeInterface;

public class UserAgentCheckerNativeInterfaceMock {
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

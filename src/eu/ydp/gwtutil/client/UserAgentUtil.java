package eu.ydp.gwtutil.client;

public class UserAgentUtil {

	public boolean matchUserAgent(String testUserAgent) {
		if (testUserAgent == null)
			return false;
		String currUserAgent = getUserAgent();
		return currUserAgent.matches(testUserAgent.toLowerCase());
	}

	private String getUserAgent() {
		String userAgent = getUserAgentString();
		if (userAgent != null) {
			userAgent = getUserAgentString().toLowerCase();
		}
		return userAgent;
	}

	public native String getUserAgentString()/*-{
												return navigator.userAgent;
												}-*/;

}

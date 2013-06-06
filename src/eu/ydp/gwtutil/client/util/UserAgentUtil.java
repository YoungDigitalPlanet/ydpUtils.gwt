package eu.ydp.gwtutil.client.util;

import eu.ydp.gwtutil.client.util.UserAgentChecker.BrowserUserAgent;
import eu.ydp.gwtutil.client.util.UserAgentChecker.MobileUserAgent;

public interface UserAgentUtil {
	public boolean isMobileUserAgent(MobileUserAgent userAgent) ;
	public boolean isMobileUserAgent(MobileUserAgent... userAgent);
	public boolean isMobileUserAgent();
	public boolean isStackAndroidBrowser();
	public boolean isUserAgent(BrowserUserAgent userAgent) ;
	public boolean isUserAgent(BrowserUserAgent... userAgents);
	public String getUserAgentStrting();
	public MobileUserAgent getMobileUserAgent();
	public boolean isLocal();
	public boolean isAIR();
	boolean isInsideIframe();

}

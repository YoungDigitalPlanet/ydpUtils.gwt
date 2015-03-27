package eu.ydp.gwtutil.client.animation.css;

enum CssAnimationPrefix {

	WEBKIT("-webkit-"), MOZ("-moz-"), W3C("");

	private final String cssPrefix;

	CssAnimationPrefix(String cssPrefix) {
		this.cssPrefix = cssPrefix;
	}

	public String toCss() {
		return cssPrefix;
	}
}

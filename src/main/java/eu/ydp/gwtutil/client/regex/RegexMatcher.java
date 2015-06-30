package eu.ydp.gwtutil.client.regex;

import com.google.gwt.regexp.shared.RegExp;

public class RegexMatcher {
    public boolean matches(String string, String regex) {
        RegExp pattern = RegExp.compile(regex);
        return pattern.test(string);
    }
}

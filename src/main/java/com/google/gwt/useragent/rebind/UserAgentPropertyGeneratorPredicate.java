package com.google.gwt.useragent.rebind;

import com.google.gwt.user.rebind.StringSourceWriter;

/**
 * DO USUNIECIA. PRZEORAC RESZTE KODU.
 */

/**
 * Represents a predicate expressed in Javascript returns the given user agent
 * if predicate evaluates to true.
 */
public class UserAgentPropertyGeneratorPredicate {

    private final StringSourceWriter predicateWriter = new StringSourceWriter();
    private final String userAgent;
    private String returnValue;

    public UserAgentPropertyGeneratorPredicate(String userAgent) {
        assert userAgent != null;
        assert userAgent.length() > 0;
        this.userAgent = userAgent;
    }

    public UserAgentPropertyGeneratorPredicate getPredicateBlock() {
        return this;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public UserAgentPropertyGeneratorPredicate indent() {
        predicateWriter.indent();
        return this;
    }

    public UserAgentPropertyGeneratorPredicate outdent() {
        predicateWriter.outdent();
        return this;
    }

    public UserAgentPropertyGeneratorPredicate println(String s) {
        predicateWriter.println(s);
        return this;
    }

    public UserAgentPropertyGeneratorPredicate returns(String s) {
        returnValue = s;
        return this;
    }

    @Override
    public String toString() {
        return predicateWriter.toString();
    }
}

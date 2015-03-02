package eu.ydp.gwtutil.client.predicate;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

public class StringPredicates {
	/**
	 * ommit empty strings and null !Strings.isNullOrEmpty(value)
	 * 
	 * @return
	 */
	public static Predicate<String> notBlank() {
		return new Predicate<String>() {

			@Override
			public boolean apply(@Nullable String value) {
				return !Strings.isNullOrEmpty(value) && !value.trim().isEmpty();
			}
		};
	}
}

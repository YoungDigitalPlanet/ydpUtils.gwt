package eu.ydp.gwtutil.client.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
/**
 * @param <B> Base type
 * @param <T> Inject if condition is met
 * @param <F> Inject if condition is not met
 */
public class ConditionalProvider<B, T extends B, F extends B> {

	@Inject Provider<T> positiveProvider;
	@Inject Provider<F> negativeProvider;
	
	public B get(boolean condition){
		if (condition){
			return positiveProvider.get();
		} else {
			return negativeProvider.get();
		}
	}
}

package eu.ydp.gwtutil.client.util;

import com.google.gwt.user.client.Random;

public class RandomWrapper {
	
	public int nextInt(int n){
		int nextInt = Random.nextInt();
		return nextInt % n;
	}
}

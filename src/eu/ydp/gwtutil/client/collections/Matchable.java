package eu.ydp.gwtutil.client.collections;

import java.util.List;

public abstract class Matchable implements Matchee {

	public boolean matches(Matchee m) {
		List<Object> thisValues = getMatchValues();
		List<Object> matcheeValues = m.getMatchValues();
		for (int i = 0; i < thisValues.size() && i < matcheeValues.size(); i++) {
			if (thisValues.get(i) != null && matcheeValues.get(i) != null) {
				return thisValues.get(i).equals(matcheeValues.get(i));
			}
		}
		return false;
	}
}

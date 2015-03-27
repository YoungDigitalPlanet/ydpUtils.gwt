package eu.ydp.gwtutil.client.geom;

import eu.ydp.gwtutil.client.PairSame;

public class Point<T extends Number> extends PairSame<T> {

	public Point(T x, T y) {
		super(x, y);
	}

	public T getX() {
		return getOne();
	}

	public T getY() {
		return getTwo();
	}

}

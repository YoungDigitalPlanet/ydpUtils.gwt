package eu.ydp.gwtutil.client.geom;

public class Range {
	
	public Range(){
	}
	
	public Range(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	private int from;
	private int to;
	
	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
	
	
	
}

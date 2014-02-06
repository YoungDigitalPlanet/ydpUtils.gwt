package eu.ydp.gwtutil.client.geom;

public class Rectangle {

	public Rectangle() {
		this.left = 0;
		this.top = 0;
		this.width = 0;
		this.height = 0;
	}

	public Rectangle(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}

	private int left;
	private int top;
	private int width;
	private int height;

	public int getTop() {
		return top;
	}

	public int getLeft() {
		return left;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRight() {
		return left + width;
	}

	public int getBottom() {
		return top + height;
	}

	public int getMiddleHorizontal() {
		return left + width / 2;
	}

	public int getMiddleVertical() {
		return top + height / 2;
	}

	public boolean contains(int x, int y) {
		return (x >= left && y >= top && x <= left + width && y <= top + height);
	}

}

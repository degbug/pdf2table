package data;

public class Location {
	private float x;
	private float y;
	private float height;
	private float width;

	public Location() {
		x = 0.0f;
		y = 0.0f;
		height = 0.0f;
		width = 0.0f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(String value) {
		setHeight(Float.parseFloat(value));
	}

	public void setWidth(String value) {
		setWidth(Float.parseFloat(value));
	}

	public void setX(String value) {
		setX(Float.parseFloat(value));
	}

	public void setY(String value) {
		setY(Float.parseFloat(value));
	}

	public void print() {
		System.out.println("X: " + x + ", Y: " + y + ", Height: " + height
				+ ", Width: " + width);

	}

	public Location copy() {
		Location loc = new Location();
		loc.setHeight(height);
		loc.setWidth(width);
		loc.setX(x);
		loc.setY(y);
		return loc;
	}
}

package paint.geom;

public class Point {
	private double pointX;
	private double pointY;
	
	public Point() {
		pointX = 0;
		pointY = 0;
	}
	
	public Point(double x, double y) {
		this.pointX = x;
		this.pointY = y;
	}

	public void setX(double x) {
		this.pointX = x;
	}
	
	public void setY(double y) {
		this.pointY = y;
	}
	
	public double getX() {
		return pointX;
	}
	
	public double getY() {
		return pointY;
	}
}

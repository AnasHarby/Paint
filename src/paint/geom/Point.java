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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(pointX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pointY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(pointX) != Double.doubleToLongBits(other.pointX))
			return false;
		if (Double.doubleToLongBits(pointY) != Double.doubleToLongBits(other.pointY))
			return false;
		return true;
	}
	
}

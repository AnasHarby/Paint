package paint.geom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Point implements Cloneable {
	private DoubleProperty propX;
	private DoubleProperty propY;

	public Point() {
		propX = new SimpleDoubleProperty(0);
		propY = new SimpleDoubleProperty(0);
	}

	public Point(double x, double y) {
		propX = new SimpleDoubleProperty(0);
		propY = new SimpleDoubleProperty(0);
		propX.set(x);
		propY.set(y);
	}

	public void setX(double x) {
		propX.set(x);
	}

	public void setY(double y) {
		propY.set(y);
	}

	public double getX() {
		return propX.doubleValue();
	}

	public double getY() {
		return propY.doubleValue();
	}

	public DoubleProperty xProperty() {
		return propX;
	}

	public DoubleProperty yProperty() {
		return propY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(propX.doubleValue());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(propY.doubleValue());
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
		if (Double.doubleToLongBits(propX.doubleValue())
				!= Double.doubleToLongBits(other.propX.doubleValue()))
			return false;
		if (Double.doubleToLongBits(propY.doubleValue())
				!= Double.doubleToLongBits(other.propY.doubleValue()))
			return false;
		return true;
	}
	
	@Override
	public Point clone() throws CloneNotSupportedException {
		return new Point(propX.doubleValue(), propY.doubleValue());
	}
}

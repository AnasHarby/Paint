package paint.geom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A point representing a location in (x, y)
 * in double precision.
 *
 */
public class Point implements Cloneable {
	private DoubleProperty propX;
	private DoubleProperty propY;

	/**
	 * Default constructor, sets double value of point
	 * with a default value of (0,0).
	 */
	public Point() {
		propX = new SimpleDoubleProperty(0);
		propY = new SimpleDoubleProperty(0);
	}

	/**
	 * Default constructor, sets double value of point
	 * with the value of (x,y).
	 * @param x point on X axis.
	 * @param y point on Y axis.
	 */
	public Point(double x, double y) {
		propX = new SimpleDoubleProperty(0);
		propY = new SimpleDoubleProperty(0);
		propX.set(x);
		propY.set(y);
	}

	/**
	 * Sets double value of point on
	 * X axis.
	 * @param x value of point on X axis.
	 */
	public void setX(double x) {
		propX.set(x);
	}

	/**
	 * Sets double value of point on
	 * Y axis.
	 * @param y value of point on Y axis.
	 */
	public void setY(double y) {
		propY.set(y);
	}

	/**
	 * Gets double value of point on
	 * X axis.
	 * @return Double value of X axis value.
	 */
	public double getX() {
		return propX.doubleValue();
	}

	/**
	 * Gets double value of point
	 * on Y axis.
	 * @return Double value of Y axis value.
	 */
	public double getY() {
		return propY.doubleValue();
	}

	/**
	 * Gets the X axis property of point.
	 * @return {@link DoubleProperty} double property on X axis.
	 */
	public DoubleProperty xProperty() {
		return propX;
	}

	/**
	 * Gets the Y axis property of point.
	 * @return {@link DoubleProperty} double property on Y axis.
	 */
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
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}
		Point other = (Point) obj;
		if (Double.doubleToLongBits(propX.doubleValue())
				!= Double.doubleToLongBits(other.propX.doubleValue())) {
			return false;
		} else if (Double.doubleToLongBits(propY.doubleValue())
				!= Double.doubleToLongBits(other.propY.doubleValue())) {
			return false;
		}
		return true;
	}

	@Override
	public Point clone() throws CloneNotSupportedException {
		return new Point(propX.doubleValue(), propY.doubleValue());
	}
}

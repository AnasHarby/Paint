package paint.shapes.util;

import paint.geom.Point;

/**
 * The properties of the circle shape.
 */
public class CircleProperties extends ShapeProperties {

	/** The KEY identifying the circle class. */
	public static final String KEY = "circle";

	/**
	 * Instantiates the properties of a circle.
	 */
	public CircleProperties() {
		key = KEY;
	}

	/* (non-Javadoc)
	 * @see paint.shapes.util.ShapeProperties#getPoint3()
	 */
	@Override
	public Point getPoint3() {
		return null;
	}

	/* (non-Javadoc)
	 * @see paint.shapes.util.ShapeProperties#setPoint3(paint.geom.Point)
	 */
	@Override
	public void setPoint3(Point point3) {
	}

}

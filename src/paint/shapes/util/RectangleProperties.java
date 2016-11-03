package paint.shapes.util;

import paint.geom.Point;

/**
 * The properties of the rectangle shape.
 */
public class RectangleProperties extends ShapeProperties {

	/** The KEY identifying the rectangle class. */
	public static final String KEY = "rectangle";

	/**
	 * Instantiates the properties of a rectangle.
	 */
	public RectangleProperties() {
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

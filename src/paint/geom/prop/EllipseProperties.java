package paint.geom.prop;

import paint.geom.Point;

/**
 * The properties of the ellipse shape.
 */
public class EllipseProperties extends ShapeProperties {

	/** The KEY identifying the ellipse class. */
	public static final String KEY = "ellipse";

	/**
	 * Instantiates the properties of an ellipse.
	 */
	public EllipseProperties() {
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

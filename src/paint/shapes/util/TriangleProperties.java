package paint.shapes.util;

import paint.geom.Point;

/**
 * The properties of the triangle shape.
 */
public class TriangleProperties extends ShapeProperties {

	/** The KEY identifying the triangle class. */
	public static final String KEY = "triangle";

	/**
	 * Instantiates the properties of a triangle.
	 */
	public TriangleProperties() {
		key = KEY;
	}

	/* (non-Javadoc)
	 * @see paint.shapes.util.ShapeProperties#getPoint3()
	 */
	@Override
	public Point getPoint3() {
		return super.point3;
	}

	/* (non-Javadoc)
	 * @see paint.shapes.util.ShapeProperties#setPoint3(paint.geom.Point)
	 */
	@Override
	public void setPoint3(Point point3) {
		super.point3 = point3;
	}

}

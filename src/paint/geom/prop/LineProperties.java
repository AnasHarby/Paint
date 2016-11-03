package paint.geom.prop;

import paint.geom.Point;

/**
 * The properties of the line shape.
 */
public class LineProperties extends ShapeProperties {

	/** The KEY identifying the line class. */
	public static final String KEY = "line";

	/**
	 * Instantiates the properties of a line.
	 */
	public LineProperties() {
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
		return;
	}

}

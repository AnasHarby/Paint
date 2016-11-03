package paint.shapes.util;

import paint.geom.Point;

/**
 * The properties of the square shape.
 */
public class SquareProperties extends ShapeProperties {

	/** The KEY identifying the square class. */
	public static final String KEY = "square";

	/**
	 * Instantiates the properties of a square.
	 */
	public SquareProperties() {
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

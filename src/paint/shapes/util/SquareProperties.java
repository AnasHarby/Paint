package paint.shapes.util;

import paint.geom.Point;

public class SquareProperties extends ShapeProperties {
	public static final String KEY = "square";

	public SquareProperties() {
		key = KEY;
	}

	@Override
	public Point getPoint3() {
		return null;
	}

	@Override
	public void setPoint3(Point point3) {
	}

}

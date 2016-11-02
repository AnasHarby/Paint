package paint.shapes.util;

import paint.geom.Point;

public class LineProperties extends ShapeProperties {
	public static final String KEY = "line";

	public LineProperties() {
		key = KEY;
	}

	@Override
	public Point getPoint3() {
		return null;
	}

	@Override
	public void setPoint3(Point point3) {
		return;
	}

}

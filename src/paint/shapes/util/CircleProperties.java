package paint.shapes.util;

import paint.geom.Point;

public class CircleProperties extends ShapeProperties {
	public static final String KEY = "circle";

	public CircleProperties() {
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

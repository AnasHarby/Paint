package paint.shapes.util;

import paint.geom.Point;

public class TriangleProperties extends ShapeProperties {
	public static final String KEY = "triangle";

	public TriangleProperties() {
		key = KEY;
	}

	@Override
	public Point getPoint3() {
		return super.point3;
	}

	@Override
	public void setPoint3(Point point3) {
		super.point3 = point3;
	}

}

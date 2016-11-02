package paint.shapes.util;

import paint.geom.Point;

public class EllipseProperties extends ShapeProperties {
	public static final String KEY = "ellipse";

	public EllipseProperties() {
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

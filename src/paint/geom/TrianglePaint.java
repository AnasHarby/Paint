package paint.geom;

import paint.geom.util.ShapeFactory;

public class TrianglePaint  extends PolygonPaint {
	private static final String KEY = "triangle";
	
	static {
		ShapeFactory.getInstance().registerShape(KEY, TrianglePaint.class);
	}
	public TrianglePaint(Point point1,
			Point point2, Point point3) {
		super(point1, point2, point3);
	}
	@Override
	public String getIconUrl() {
		return null;
	}
}

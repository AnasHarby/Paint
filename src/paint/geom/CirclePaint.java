package paint.geom;

import paint.geom.util.ShapeFactory;

public class CirclePaint extends EllipsePaint {
	private Point centerCircle;
	private double radiusCircle;
	private static final String KEY = "circle";

	static {
		ShapeFactory.getInstance().registerShape(KEY, CirclePaint.class);
	}

	public CirclePaint(Point center, double radius) {
		super(center, radius, radius);
		centerCircle = center;
		radiusCircle = radius;
	}

	@Override
	public String getIconUrl() {
		return null;
	}
	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		radiusCircle = getRadius(x2, y2);
		super.ellipse.setRadiusX(radiusCircle);
		super.ellipse.setRadiusY(radiusCircle);
		super.setVertices();
	}
	private double getRadius(double x, double y) {
		double dX = Math.abs(centerCircle.getX() - x);
		double dY = Math.abs(centerCircle.getY() - y);
		double radius = Math.sqrt(dX * dX + dY * dY);
		return radius;
	}
}

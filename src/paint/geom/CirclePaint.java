package paint.geom;

import paint.geom.util.ShapeFactory;

public class CirclePaint extends EllipsePaint implements Cloneable {
	private Point centerCircle;
	private double radiusCircle;
	public static final String KEY = "circle";
	private static final int CENTER_X = 0;
	private static final int CENTER_Y = 1;
	private static final int CIRCUM_X = 2;
	private static final int CIRCUM_Y = 3;
	
	static {
		ShapeFactory.getInstance().registerShape(KEY, CirclePaint.class);
	}

	public CirclePaint(Point center, double radius) {
		super(center, radius, radius);
		centerCircle = center;
		radiusCircle = radius;
	}
	
	public CirclePaint(double... properties) {
		this(new Point(properties[CENTER_X], properties[CENTER_Y]),
				getRadius(properties[CENTER_X], properties[CENTER_Y],
				properties[CIRCUM_X], properties[CIRCUM_Y]));
	}

	@Override
	public String getIconUrl() {
		return null;
	}
	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		radiusCircle = getRadius(centerCircle.getX(), centerCircle.getY(), x2, y2);
		super.ellipse.setRadiusX(radiusCircle);
		super.ellipse.setRadiusY(radiusCircle);
	}
	private static double getRadius(double centerX, double centerY, double x, double y) {
		double dX = Math.abs(centerX - x);
		double dY = Math.abs(centerY - y);
		double radius = Math.sqrt(dX * dX + dY * dY);
		return radius;
	}

	@Override
	public CirclePaint clone() throws CloneNotSupportedException {
		CirclePaint newObject = new CirclePaint(centerCircle.clone(), radiusCircle);
		newObject.ellipse.setTranslateX(super.ellipse.getTranslateX());
		newObject.ellipse.setTranslateY(super.ellipse.getTranslateY());
		newObject.ellipse.setRotate(super.ellipse.getRotate());
		newObject.ellipse.setFill(super.ellipse.getFill());
		newObject.ellipse.setStroke(super.ellipse.getStroke());
		newObject.ellipse.setStrokeWidth(super.ellipse.getStrokeWidth());
		return newObject;
	}
}

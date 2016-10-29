package paint.geom;

import javafx.scene.shape.Ellipse;

public class CirclePaint extends EllipsePaint {
	private Point centerCircle;
	private double radiusCircle;

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
		Ellipse newCircle = new Ellipse();
		radiusCircle = getRadius(x2, y2);
		newCircle.setCenterX(centerCircle.getX());
		newCircle.setCenterY(centerCircle.getY());
		newCircle.setRadiusX(radiusCircle);
		newCircle.setRadiusY(radiusCircle);
		super.ellipse = newCircle;
	}
	private double getRadius(double x, double y) {
		double dX = Math.abs(centerCircle.getX() - x);
		double dY = Math.abs(centerCircle.getY() - y);
		double radius = Math.sqrt(dX * dX + dY * dY);
		return radius;
	}

}

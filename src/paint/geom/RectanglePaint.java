package paint.geom;

import javafx.scene.paint.Color;

public class RectanglePaint extends PolygonPaint {
	private Point upperLeftPoint;

	public RectanglePaint(Point upperLeft,
		double width, double height) {
			super(upperLeft,
				new Point(upperLeft.getX()
				, upperLeft.getY() + height),
				new Point(upperLeft.getX() + width
						, upperLeft.getY() + height),
				new Point(upperLeft.getX() + width,
								upperLeft.getY()));
			setUpperLeftPoint(upperLeft);
		super.setBorderColor(Color.BLACK);
		super.fill(Color.TRANSPARENT);

	}

	public RectanglePaint(Point upperLeft,
			Point lowerLeft, Point lowerRight, Point upperRight) {
		super(upperLeft, lowerLeft, lowerRight, upperRight);
	}

	@Override
	public String getIconUrl() {
		return null;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		for (int i = 0;
				i < super.polygon.getPoints().size() - 1; i += 2) {
			double x = super.polygon.getPoints().get(i);
			double y = super.polygon.getPoints().get(i + 1);
			if (x == x1 && y == y1) {
				super.polygon.getPoints().set(i, x2);
				super.polygon.getPoints().set(i + 1, y2);
			} else if (x == x1) {
				super.polygon.getPoints().set(i, x2);
			} else if (y == y1) {
				super.polygon.getPoints().set(i + 1, y2);
			}
		}
	}
}

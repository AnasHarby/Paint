package paint.geom;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

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
		Polygon newPolygon = new Polygon();
		for (Point vertix : super.polygonVertices) {
			if (vertix.getX() == x1 && vertix.getY() == y1) {
				vertix.setX(x2);
				vertix.setY(y2);
			} else if (vertix.getX() == x1) {
				vertix.setX(x2);
			} else if (vertix.getY() == y1) {
				vertix.setY(y2);
			}
			newPolygon.getPoints().add(vertix.getX());
			newPolygon.getPoints().add(vertix.getY());
		}
		super.polygon = newPolygon;
		super.fill(Color.TRANSPARENT);
		super.setBorderColor(Color.BLACK);
		super.setActionHandlers();
	}
}

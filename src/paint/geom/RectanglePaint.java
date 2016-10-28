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
			upperLeftPoint = upperLeft;
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
		
	private Point getRectangleUpperLeft(double x1, double y1, double x2, double y2) {
		return new Point(Math.min(x1, x2), Math.min(y1, y2));
	}
}

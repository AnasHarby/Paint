package paint.geom;

import javafx.scene.paint.Color;
import paint.geom.util.ShapeFactory;

public class RectanglePaint extends PolygonPaint implements Cloneable {
	private Point upperLeftPoint;
	private double width;
	private double height;
	private static final int UPPER_LEFT_X = 0;
	private static final int UPPER_LEFT_Y = 1;
	private static final int BOTTOM_RIGHT_X = 2;
	private static final int BOTTOM_RIGHT_Y = 3;
	public static final String KEY = "rectangle";

	static {
		ShapeFactory.getInstance().registerShape(KEY, RectanglePaint.class);
	}

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
		this.width = width;
		this.height = height;
	}
	
	public RectanglePaint(double... properties) {
		this(new Point(Math.min(properties[UPPER_LEFT_X], properties[BOTTOM_RIGHT_X]),
				Math.min(properties[UPPER_LEFT_Y], properties[BOTTOM_RIGHT_Y])),
				Math.abs(properties[UPPER_LEFT_X] - properties[BOTTOM_RIGHT_X]),
				Math.abs(properties[UPPER_LEFT_Y] - properties[BOTTOM_RIGHT_Y]));
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
	
	@Override
	public RectanglePaint clone() throws CloneNotSupportedException {
		RectanglePaint newObject = new RectanglePaint(upperLeftPoint.clone(), width, height);
		newObject.polygon.setTranslateX(polygon.getTranslateX());
		newObject.polygon.setTranslateY(polygon.getTranslateY());
		newObject.polygon.setRotate(polygon.getRotate());
		newObject.polygon.setFill(polygon.getFill());
		newObject.polygon.setStroke(polygon.getStroke());
		newObject.polygon.setStrokeWidth(polygon.getStrokeWidth());
		return newObject;
	}
}

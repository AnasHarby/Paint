package paint.geom;

import java.util.Random;

import javafx.scene.paint.Color;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.ShapeProperties;
import paint.shapes.util.SquareProperties;

public class SquarePaint extends RectanglePaint implements Cloneable {
	public static final String KEY = "square";
	private static final int UPPER_LEFT_X = 0;
	private static final int UPPER_LEFT_Y = 1;
	private static final int BOTTOM_RIGHT_X = 2;
	private static final int BOTTOM_RIGHT_Y = 3;
	private Point upperLeft;
	private double sideLength;

	static {
		ShapeFactory.getInstance().registerShape(KEY, SquarePaint.class);
	}

	public SquarePaint(Point upperLeft, double length) {
		super(upperLeft, length, length);
		this.upperLeft = upperLeft;
		this.sideLength = length;
		polygon.setId(KEY + new Random().nextInt());
	}

	public SquarePaint(double... properties) {
		this(new Point(Math.min(properties[UPPER_LEFT_X], properties[BOTTOM_RIGHT_X]),
				Math.min(properties[UPPER_LEFT_Y], properties[BOTTOM_RIGHT_Y])),
				Math.abs(properties[UPPER_LEFT_X]
						- properties[BOTTOM_RIGHT_X]));
	}

	public SquarePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY());
		polygon.setStroke(properties.getStrokeColor());
		polygon.setFill(properties.getFillColor());
		polygon.setStrokeWidth(properties.getStrokeWidth());
		polygon.setTranslateX(properties.getTranslateX());
		polygon.setTranslateY(properties.getTranslateY());
		polygon.setId(properties.getId());
		rotate(properties.getRotation());
	}

	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		double xOpposite = 0;
		double yOpposite = 0;
		for (Point point : super.points) {
			if (x1 != point.getX()
					&& y1 != point.getY()) {
				xOpposite = point.getX();
				yOpposite = point.getY();
			}
		}
		double m = (y1 - yOpposite)
				/ (x1 - xOpposite);
		double x = (m / (m * m + 1)) * (m * x1 + x2 / m + y2 - y1);
		double y = y1 + m * x - m * x1;
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		int i = 0;
		for (Point point : super.points) {
			if (x1 == point.getX()
					&& y1 == point.getY()) {
				point.setX(x);
				point.setY(y);
			} else if (x1 == point.getX()) {
				double dy = y1 - y;
				point.setX(x);
				point.setY(point.getY() + dy);
			} else if (y1 == point.getY()) {
				double dx = x1 - x;
				point.setX(point.getX() + dx);
				point.setY(y);
			} else {
				double dx = x1 - x;
				double dy = y1 - y;
				point.setX(point.getX() + dx);
				point.setY(point.getY() + dy);
			}
			super.polygon.getPoints().set(i * 2, point.getX());
			super.polygon.getPoints().set(i * 2 + 1, point.getY());
			minX = Math.min(minX, point.getX());
			minY = Math.min(minY, point.getY());
			maxX = Math.max(maxX, point.getX());
			i++;
		}
		super.setUpperLeftPoint(new Point(minX, minY));
		super.setWidth(maxX - minX);
		super.setHeight(maxX - minX);
	}

	@Override
	public SquarePaint clone() throws CloneNotSupportedException {
		SquarePaint newObject
		= new SquarePaint(upperLeft.clone(), sideLength);
		newObject.polygon.setTranslateX(polygon.getTranslateX());
		newObject.polygon.setTranslateY(polygon.getTranslateY());
		newObject.polygon.setRotate(polygon.getRotate());
		Color col = (Color) polygon.getFill();
		newObject.polygon.setFill(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		col = (Color) polygon.getStroke();
		newObject.polygon.setStroke(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		newObject.polygon.setStrokeWidth(polygon.getStrokeWidth());
		newObject.polygon.setOnMouseClicked(polygon.getOnMouseClicked());
		newObject.rotate(rotation);
		return newObject;
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new SquareProperties();
		prop.setFillColor(polygon.getFill());
		prop.setStrokeColor(polygon.getStroke());
		try {
			prop.setPoint1(upperLeft.clone());
			prop.setPoint2(new Point(upperLeft.getX() + sideLength,
					upperLeft.getY() + sideLength));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setId(polygon.getId());
		prop.setStrokeWidth(polygon.getStrokeWidth());
		prop.setTranslateX(polygon.getTranslateX());
		prop.setTranslateY(polygon.getTranslateY());
		prop.setRotation(rotation);
		return prop;
	}
}

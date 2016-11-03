package paint.geom;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.RectangleProperties;
import paint.shapes.util.ShapeProperties;

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
		super.setFill(Color.TRANSPARENT);
		this.width = width;
		this.height = height;
		setResizerRotation();
		polygon.setId(KEY + new Random().nextInt());
	}

	public RectanglePaint(double... properties) {
		this(new Point(Math.min(properties[UPPER_LEFT_X], properties[BOTTOM_RIGHT_X]),
				Math.min(properties[UPPER_LEFT_Y], properties[BOTTOM_RIGHT_Y])),
				Math.abs(properties[UPPER_LEFT_X] - properties[BOTTOM_RIGHT_X]),
				Math.abs(properties[UPPER_LEFT_Y] - properties[BOTTOM_RIGHT_Y]));
	}

	public RectanglePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY());
		polygon.setStroke(properties.getStrokeColor());
		polygon.setFill(properties.getFillColor());
		polygon.setStrokeWidth(properties.getStrokeWidth());
		polygon.setRotate(properties.getRotation());
		polygon.setTranslateX(properties.getTranslateX());
		polygon.setTranslateY(properties.getTranslateY());
		System.out.println(properties.getId());
		polygon.setId(properties.getId());
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
	public void setTranslateX(double translateX) {
		polygon.setTranslateX(translateX);
	}

	@Override
	public void setTranslateY(double translateY) {
		polygon.setTranslateY(translateY);
	}

	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		int i = 0;
		double minX = Double.MAX_VALUE;
		double maxX = 0;
		double minY = Double.MAX_VALUE;
		double maxY = 0;
		for (Point point : super.points) {
			if (x1 == point.getX()
					&& y1 == point.getY()) {
				point.setX(x2);
				point.setY(y2);
			} else if (x1 == point.getX()) {
				point.setX(x2);
			} else if (y1 == point.getY()) {
				point.setY(y2);
			}
			super.polygon.getPoints().set(i * 2, point.getX());
			super.polygon.getPoints().set(i * 2 + 1, point.getY());
			minX = Math.min(minX, point.getX());
			minY = Math.min(minX, point.getY());
			maxX = Math.max(maxX, point.getX());
			maxY = Math.max(maxY, point.getY());
			i++;
		}
		this.width = maxX - minX;
		this.height = maxY - minY;
		setUpperLeftPoint(new Point(minX, minY));
		setResizerRotation();
	}

	@Override
	public RectanglePaint clone() throws CloneNotSupportedException {
		RectanglePaint newObject = new RectanglePaint(
				upperLeftPoint.clone(), width, height);
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
		return newObject;
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new RectangleProperties();
		prop.setFillColor(polygon.getFill());
		prop.setStrokeColor(polygon.getStroke());
		try {
			prop.setPoint1(upperLeftPoint.clone());
			prop.setPoint2(new Point(upperLeftPoint.getX() + width,
					upperLeftPoint.getY() + height));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setId(polygon.getId());
		prop.setStrokeWidth(polygon.getStrokeWidth());
		prop.setRotation(polygon.getRotate());
		prop.setTranslateX(polygon.getTranslateX());
		prop.setTranslateY(polygon.getTranslateY());
		return prop;
	}

	private void setResizerRotation() {
		for (Resizer resizer : super.resizers) {
			resizer.setRotationPivot(getCenter());
		}
	}

	private Point getCenter() {
		double x = upperLeftPoint.getX()
				+ width / 2;
		double y = upperLeftPoint.getY()
				+ height / 2;
		return new Point(x, y);
	}

	@Override
	public void rotate(double angle) {
		super.polygon.getTransforms().add(new Rotate(
				angle, getCenter().getX()
				, getCenter().getY()));
		for (Resizer resizer : resizers) {
			resizer.rotate(angle);
		}
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}

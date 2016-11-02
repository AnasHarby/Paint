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
		this(new Point(Math.min(properties[UPPER_LEFT_X],
				properties[BOTTOM_RIGHT_X]),
				Math.min(properties[UPPER_LEFT_Y],
						properties[BOTTOM_RIGHT_Y])),
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
		polygon.setRotate(properties.getRotation());
		polygon.setTranslateX(properties.getTranslateX());
		polygon.setTranslateY(properties.getTranslateY());
	}

	@Override
	public String getIconUrl() {
		return null;
	}

	@Override
	public SquarePaint clone() throws CloneNotSupportedException {
		SquarePaint newObject = new SquarePaint(upperLeft.clone(), sideLength);
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

		prop.setStrokeWidth(polygon.getStrokeWidth());
		prop.setRotation(polygon.getRotate());
		prop.setTranslateX(polygon.getTranslateX());
		prop.setTranslateY(polygon.getTranslateY());
		return prop;
	}
}

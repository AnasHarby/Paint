package paint.geom;

import java.util.Random;

import javafx.scene.paint.Color;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.CircleProperties;
import paint.shapes.util.ShapeProperties;

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
		ellipse.setId(KEY + new Random().nextInt());
	}

	public CirclePaint(double... properties) {
		this(new Point(properties[CENTER_X], properties[CENTER_Y]),
				getRadius(properties[CENTER_X], properties[CENTER_Y],
						properties[CIRCUM_X], properties[CIRCUM_Y]));
	}

	public CirclePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY());
		ellipse.setStroke(properties.getStrokeColor());
		ellipse.setFill(properties.getFillColor());
		ellipse.setStrokeWidth(properties.getStrokeWidth());
		ellipse.setRotate(properties.getRotation());
		ellipse.setTranslateX(properties.getTranslateX());
		ellipse.setTranslateY(properties.getTranslateY());
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
		Color col = (Color) ellipse.getFill();
		newObject.ellipse.setFill(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		col = (Color) ellipse.getStroke();
		newObject.ellipse.setStroke(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		newObject.ellipse.setStrokeWidth(super.ellipse.getStrokeWidth());
		return newObject;
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new CircleProperties();
		prop.setFillColor(ellipse.getFill());
		prop.setStrokeColor(ellipse.getStroke());
		try {
			prop.setPoint1(centerCircle.clone());
			prop.setPoint2(new Point(centerCircle.getX() + radiusCircle, centerCircle.getY()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setStrokeWidth(ellipse.getStrokeWidth());
		prop.setRotation(ellipse.getRotate());
		prop.setTranslateX(ellipse.getTranslateX());
		prop.setTranslateY(ellipse.getTranslateY());
		return prop;
	}
}

package paint.geom;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.CircleProperties;
import paint.shapes.util.ShapeProperties;

/**
 * The Ellipse class creates a new circle
 * with the specified radius and location in pixels.
 * <br>
 * Inherits from {@link EllipsePaint}.
 * Implements {@link Cloneable} interface.
 */
public class CirclePaint extends EllipsePaint implements Cloneable {

	/**
	 * The key of the circle class, used for getting
	 * {@link ShapePaint} instances from {@link ShapeFactory}.
	 */
	public static final String KEY = "circle";
	private static final int CENTER_X = 0;
	private static final int CENTER_Y = 1;
	private static final int CIRCUM_X = 2;
	private static final int CIRCUM_Y = 3;
	private Point centerCircle;
	private double radiusCircle;

	static {
		ShapeFactory.getInstance().registerShape(KEY, CirclePaint.class);
	}

	/**
	 * Default constructor used for construction of {@link CirclePaint}.
	 * @param center {@link Point} representing the center of ellipse on {@link Pane}.
	 * @param radius radius of circle.
	 */
	public CirclePaint(Point center, double radius) {
		super(center, radius, radius);
		centerCircle = center;
		radiusCircle = radius;
		ellipse.setId(KEY + new Random().nextInt());
	}

	/**
	 * Default constructor for construction of {@link CirclePaint}
	 * by using an equivalent
	 * <br>
	 * {@link ShapeProperties} object containing all specifications of the circle.
	 * @param properties {@link ShapeProperties} object.
	 */
	public CirclePaint(double... properties) {
		this(new Point(properties[CENTER_X], properties[CENTER_Y]),
				getRadius(properties[CENTER_X], properties[CENTER_Y],
						properties[CIRCUM_X], properties[CIRCUM_Y]));
	}

	/**
	 * Default constructor for construction of {@link CirclePaint}
	 * by using a collection
	 * <br>
	 * of double values representing center and a point on the circumference of circle.
	 * @param properties properties of {@link EllipsePaint}
	 */
	public CirclePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY());
		ellipse.setStroke(properties.getStrokeColor());
		ellipse.setFill(properties.getFillColor());
		ellipse.setStrokeWidth(properties.getStrokeWidth());
		ellipse.setTranslateX(properties.getTranslateX());
		ellipse.setTranslateY(properties.getTranslateY());
		ellipse.setId(properties.getId());
		rotate(properties.getRotation());
	}

	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		radiusCircle = getRadius(centerCircle.getX(), centerCircle.getY(), x2, y2);
		super.ellipse.setRadiusX(radiusCircle);
		super.ellipse.setRadiusY(radiusCircle);
		super.setVertices();
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new CircleProperties();
		prop.setFillColor(ellipse.getFill());
		prop.setStrokeColor(ellipse.getStroke());
		try {
			prop.setPoint1(centerCircle.clone());
			prop.setPoint2(new Point(centerCircle.getX()
					+ radiusCircle, centerCircle.getY()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setStrokeWidth(ellipse.getStrokeWidth());
		prop.setTranslateX(ellipse.getTranslateX());
		prop.setTranslateY(ellipse.getTranslateY());
		prop.setId(ellipse.getId());
		prop.setRotation(rotation);
		return prop;
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
		newObject.ellipse.setOnMouseClicked(ellipse.getOnMouseClicked());
		newObject.rotate(rotation);
		return newObject;
	}

	private static double getRadius(double centerX, double centerY, double x, double y) {
		double dX = Math.abs(centerX - x);
		double dY = Math.abs(centerY - y);
		double radius = Math.sqrt(dX * dX + dY * dY);
		return radius;
	}
}

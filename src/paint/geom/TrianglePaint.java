package paint.geom;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import paint.geom.prop.ShapeProperties;
import paint.geom.prop.TriangleProperties;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeFactory;
/**
 * The Square class creates a new square
 * with the coordinates of the specified vertices in pixels.
 * <br>
 * extends {@link paint.geom.PolygonPaint} class
 * and implements {@link Cloneable} interface.
 */
public class TrianglePaint  extends PolygonPaint implements Cloneable {
	/**
	 * The key of the triangle class, used for getting
	 * {@link ShapePaint} instances from {@link ShapeFactory}.
	 */
	public static final String KEY = "triangle";
	private static final int FIRST_X = 0;
	private static final int FIRST_Y = 1;
	private static final int SECOND_X = 2;
	private static final int SECOND_Y = 3;
	private static final int THIRD_X = 4;
	private static final int THIRD_Y = 5;
	private Point point1;
	private Point point2;
	private Point point3;
	private double rotation = 0;

	static {
		ShapeFactory.getInstance().registerShape(KEY, TrianglePaint.class);
	}
	/**
	 * Default constructor for construction of {@link TrianglePaint}.
	 * using its vertices.
	 * @param point1 The first vertex of the triangle
	 * @param point2 The second vertex of the triangle
	 * @param point3 The third vertex of the triangle
	 */
	public TrianglePaint(Point point1,
			Point point2, Point point3) {
		super(point1, point2, point3);
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		setResizerRotation();
		polygon.setId(KEY + new Random().nextInt());
	}
	/**
	 * Default constructor for construction of {@link TrianglePaint}.
	 * using its vertices.
	 * @param properties The vertices of the triangle in
	 * order x1, y1, x2, y2, x3, y3
	 */
	public TrianglePaint(double... properties) {
		super(properties);
		point1 = new Point(properties[FIRST_X], properties[FIRST_Y]);
		point2 = new Point(properties[SECOND_X], properties[SECOND_Y]);
		point3 = new Point(properties[THIRD_X], properties[THIRD_Y]);
		setResizerRotation();
		polygon.setId(KEY + new Random().nextInt());
	}
	/**
	 * The constructor for construction of {@link TrianglePaint}
	 * by using an equivalent
	 * <br>
	 * {@link ShapeProperties} object
	 * containing all specifications of the triangle.
	 * @param properties {@link ShapeProperties} object.
	 */
	public TrianglePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY(),
				properties.getPoint3().getX(),
				properties.getPoint3().getY());
		polygon.setStroke(properties.getStrokeColor());
		polygon.setFill(properties.getFillColor());
		polygon.setStrokeWidth(properties.getStrokeWidth());
		polygon.setTranslateX(properties.getTranslateX());
		polygon.setTranslateY(properties.getTranslateY());
		polygon.setId(properties.getId());
		rotate(properties.getRotation());
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
	public TrianglePaint clone() throws CloneNotSupportedException {
		TrianglePaint newObject = new TrianglePaint(point1.clone(),
				point2.clone(), point3.clone());
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
		ShapeProperties prop = new TriangleProperties();
		prop.setFillColor(polygon.getFill());
		prop.setStrokeColor(polygon.getStroke());
		try {
			prop.setPoint1(point1.clone());
			prop.setPoint2(point2.clone());
			prop.setPoint3(point3.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setStrokeWidth(polygon.getStrokeWidth());
		prop.setTranslateX(polygon.getTranslateX());
		prop.setTranslateY(polygon.getTranslateY());
		prop.setId(polygon.getId());
		prop.setRotation(rotation);
		return prop;
	}
	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		super.resize(x1, y1, x2, y2);
		point1.setX(super.polygon.getPoints().get(FIRST_X));
		point1.setY(super.polygon.getPoints().get(FIRST_Y));
		point2.setX(super.polygon.getPoints().get(SECOND_X));
		point2.setY(super.polygon.getPoints().get(SECOND_Y));
		point3.setX(super.polygon.getPoints().get(THIRD_X));
		point3.setY(super.polygon.getPoints().get(THIRD_Y));
	}


	@Override
	public void rotate(double angle) {
		rotation += angle;
		super.polygon.getTransforms().add(new Rotate(
				angle, getCenter().getX(), getCenter().getY()));
		for (Resizer resizer : super.resizers) {
			resizer.rotate(angle);
		}
	}
	/**
	 * Gets the center of the triangle.
	 * @return the center of the triangle
	 */
	private Point getCenter() {
		double x = 0;
		double y = 0;
		for (Point point : super.points) {
			x = point.getX();
			y = point.getY();
		}
		x /= 3;
		y /= 3;
		return new Point(x, y);
	}
	/**
	 * Sets the axis for rotating a resizer
	 */
	private void setResizerRotation() {
		for (Resizer resizer : super.resizers) {
			resizer.setRotationPivot(getCenter());
		}
	}
}

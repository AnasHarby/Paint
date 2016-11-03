package paint.geom.prop;

import javafx.scene.paint.Paint;
import paint.geom.Point;

/**
 * The properties of a shape. these
 * properties can be used for constructing
 * a new shape.
 */
public abstract class ShapeProperties {

	/** The first point in the
	 * shape definition.
	 */
	protected Point point1;

	/** The second point in the
	 * shape definition.
	 */
	protected Point point2;

	/** The third point in the
	 * shape definition
	 */
	protected Point point3;

	/** The x translation of the shape. */
	protected double translateX;

	/** The y translation of the shape. */
	protected double translateY;

	/** The rotation of the shape. */
	protected double rotation;

	/** The stroke width of the shape. */
	protected double strokeWidth;

	/** The stroke color of the shape. */
	protected Paint strokeColor;

	/** The fill color of the shape. */
	protected Paint fillColor;

	/** The key identifying the shape type. */
	protected String key;

	/** The id of the shape object. */
	protected String id;

	/**
	 * Gets the first point in the
	 * shape definition.
	 * @return The first point in the
	 * shape definition
	 */
	public Point getPoint1() {
		return point1;
	}

	/**
	 * Sets the first point in the
	 * shape definition.
	 * @param point1 The first point in the
	 * shape definition
	 */
	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	/**
	 * Gets the second point in the
	 * shape definition.
	 * @return The second point in the
	 * shape definition
	 */
	public Point getPoint2() {
		return point2;
	}

	/**
	 * Sets the second point in the
	 * shape definition.
	 * @param point2 The second point in the
	 * shape definition
	 */
	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	/**
	 * Gets the third point in the
	 * shape definition (will return
	 *  null if the shape is not a triangle).
	 * @return The second point in the
	 * shape definition
	 */
	public abstract Point getPoint3();

	/**
	 * Sets the third point in the
	 * shape definition (will do
	 * nothing if the shape is not a triangle).
	 * @param point3 The second point in the
	 * shape definition
	 */
	public abstract void setPoint3(Point point3);

	/**
	 * Gets the x translation of the shape.
	 * @return the x translation of the shape
	 */
	public double getTranslateX() {
		return translateX;
	}

	/**
	 * Sets the x translation of the shape.
	 * @param translateX the new x
	 * translation of the shape
	 */
	public void setTranslateX(double translateX) {
		this.translateX = translateX;
	}

	/**
	 * Gets the y translation of the shape.
	 * @return the y translation of the shape
	 */
	public double getTranslateY() {
		return translateY;
	}

	/**
	 * Sets the y translation of the shape.
	 * @param translateX the new y
	 * translation of the shape
	 */
	public void setTranslateY(double translateY) {
		this.translateY = translateY;
	}

	/**
	 * Gets the rotation of the shape.
	 * @return the rotation
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Sets the rotation of the shape.
	 * @param rotation the new rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	/**
	 * Gets the stroke width of the shape.
	 * @return the stroke width of the shape
	 */
	public double getStrokeWidth() {
		return strokeWidth;
	}

	/**
	 * Sets the stroke width of the shape.
	 * @param strokeWidth the new stroke width
	 */
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	/**
	 * Gets the stroke color of the shape.
	 * @return the stroke color
	 */
	public Paint getStrokeColor() {
		return strokeColor;
	}

	/**
	 * Sets the stroke color of the shape.
	 * @param strokeColor the new stroke color
	 */
	public void setStrokeColor(Paint strokeColor) {
		this.strokeColor = strokeColor;
	}

	/**
	 * Gets the fill color of the shape.
	 * @return the fill color
	 */
	public Paint getFillColor() {
		return fillColor;
	}

	/**
	 * Sets the fill color of the shape.
	 * @param paint the new fill color
	 */
	public void setFillColor(Paint paint) {
		this.fillColor = paint;
	}

	/**
	 * Gets the key identifying the shape type.
	 * @return the key identifying the shape
	 * type
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the id of the shape object.
	 * @return the id of the shape object
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the shape object.
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
}

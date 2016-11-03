package paint.geom;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import paint.geom.prop.ShapeProperties;

/**
 * The ShapePaint interface provides definitions
 * for objects that represent a geometric shape.
 */
public interface ShapePaint {
	/**
	 * Draws shape into the parent pane.
	 * {@link javafx.scene.layout.Pane}.
	 * @param contentPane Parent pane.
	 */
	public void draw(Pane contentPane);

	/**
	 * Removes shape from the parent pane.
	 * {@link javafx.scene.layout.Pane}.
	 * @param contentPane Parent pane.
	 */
	public void remove(Pane contentPane);

	/**
	 * Sets border color for shape.
	 * @param col {@link javafx.scene.paint.Color} Color.
	 */
	public void setBorderColor(Color col);

	/**
	 * Fills shape area with a color.
	 * @param col {@link javafx.scene.paint.Color} Color.
	 */
	public void setFill(Color col);

	/**
	 * Gets filling color of a shape.
	 * @return {@link javafx.scene.paint.Color} Color.
	 */
	public Color getFill();

	/**
	 * Sets border width for shape.
	 * @param width Border width
	 */
	public void setBorderWidth(double width);

	/**
	 * Moves shape to another point on pane
	 * given the two parameters (x, y).
	 * @param x x-axis value for point.
	 * @param y y-axis value for point.
	 */
	public void move(double x, double y);

	/**
	 * Rotates shape around its center pivot
	 * with a give angle.
	 * @param angle Rotation angle.
	 */
	public void rotate(double angle);

	/**
	 * Pushes shape to back of the pane.
	 */
	public void toBack();

	/**
	 * Pulls shape to front of the pane.
	 */
	public void toFront();

	/**
	 * Resizes shape given a reference point (x1, y1)
	 * and a point to resize to (x2, y2).
	 * @param x1 x-axis value of the reference point
	 * @param y1 y-axis value of the reference point
	 * @param x2 x-axis value of the resizing point
	 * @param y2 y-axis value of the resizing point
	 */
	public void resize(double x1, double y1, double x2, double y2);

	/**
	 * Sets translate of shape on x-Axis.
	 * @param translateX Double value of translate on x-Axis.
	 */
	public void setTranslateX(double translateX);

	/**
	 * Sets translate of shape on y-Axis.
	 * @param translateY Double value of translate on y-Axis.
	 */
	public void setTranslateY(double translateY);

	/**
	 * Show ({@link paint.geom.util.Resizer} that allow
	 * user to resize a shape with mouse dragging.
	 */
	public void showResizers();

	/**
	 * Hide ({@link paint.geom.util.Resizer} that allow
	 * user to resize a shape with mouse dragging.
	 */
	public void hideResizers();

	/**
	 * Sets the value of the property onMouseClicked.
	 * Defines a function to be called when clicking on this shape.
	 * @param handler {@link EventHandler} Event handler to mouse click.
	 */
	public void setOnMouseClicked(EventHandler<MouseEvent> handler);

	/**
	 * Sets the value of the property onMousePressed.
	 * <br>
	 * Defines a function to be called when pressing this shape.
	 * @param handler {@link EventHandler} Event handler to mouse press.
	 */
	public void setOnMousePressed(EventHandler<MouseEvent> handler);

	/**
	 * Sets the value of the property onMouseDragged.
	 * <br>
	 * Defines a function to be called when dragging this shape.
	 * @param handler {@link EventHandler} Event handler to mouse drag.
	 */
	public void setOnMouseDragged(EventHandler<MouseEvent> handler);

	/**
	 * Sets the value of the property onMouseReleased.
	 * <br>
	 * Defines a function to be called when a mouse button
	 * has been released from this shape.
	 * @param handler {@link EventHandler} Event handler to mouse release.
	 */
	public void setOnMouseReleased(EventHandler<MouseEvent> handler);

	/**
	 * The id of this <strong>Shape</strong>.
	 * This simple string identifier is useful for finding a specific Shape
	 * within the scene pane.
	 * @return ID of this shape.
	 */
	public String getId();

	/**
	 * Gets a container to this shape's properties.
	 * @return {@link ShapeProperties} Instance of this shape's properties.
	 */
	public ShapeProperties getShapeProperties();

	/**
	 * Gets a clone to this shape with the same properties.
	 * @return Clone to this shape.
	 * @throws CloneNotSupportedException Exception for non-cloneable objects.
	 */
	public ShapePaint clone() throws CloneNotSupportedException;
}


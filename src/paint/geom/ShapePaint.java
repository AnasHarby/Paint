package paint.geom;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import paint.shapes.util.ShapeProperties;


public interface ShapePaint {
	/**
	 * Draws shape into the parent pane
	 * {@link javafx.scene.layout.Pane}.
	 * @param contentPane Parent pane
	 */
	public void draw(Pane contentPane);
	/**
	 * Removes shape from the parent pane
	 * {@link javafx.scene.layout.Pane}.
	 * @param contentPane Parent pane
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
	public String getIconUrl();
	/**
	 * Sets border width for shape.
	 * @param width Border width
	 */
	public void setBorderWidth(double width);
	/**
	 * Moves shape to another point on pane
	 * given the two parameters (x, y).
	 * @param x x-axis value for point
	 * @param y y-axis value for point
	 */
	public void move(double x, double y);
	/**
	 * Pushes shape to back of the pane.
	 */
	public void rotate(double angle);
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
	public void setTranslateX(double translateX);
	public void setTranslateY(double translateY);
	public void showResizers();
	public void hideResizers();
	public void setOnMouseClicked(EventHandler<MouseEvent> handler);
	public String getId();
	public ShapeProperties getShapeProperties();
	public ShapePaint clone() throws CloneNotSupportedException;
}


package paint.geom;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
	 * @param col {@link javafx.scene.paint.Color} color
	 */
	public void setBorderColor(Color col);
	/**
	 * Fills shape area with a color.
	 * @param col {@link javafx.scene.paint.Color} color
	 */
	public void fill(Color col);

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
	 * Shows the reference resizing points as rectangles
	 * on shape by adding them to the parent pane.
	 * @param contentPane Parent pane.
	 */
	public void showResizers(Pane contentPane);
	/**
	 * Hides the reference resizing points from
	 * the parent pane.
	 * @param contentPane Parent pane.
	 */
	public void hideResizers(Pane contentPane);
}

package paint.geom.util;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import paint.geom.Point;
import paint.geom.ShapePaint;
/**
 * A resizer model that gets binded
 * to a shape and resizes it by dragging.
 */
public class Resizer {
	/**
	 * The radius of the resizer
	 */
	private static final double SIZE = 4;
	private Circle circ;
	private Point rotationPivot;
	/**
	 * The constructor for constructing a new
	 * resizer given the shape it will be attached to,
	 * the model of the shape and the point of attachement.
	 * @param shape The shape that the resizer will attach to
	 * @param shapePaint The model of the shape
	 * @param point The point of attachement
	 */
	public Resizer(Shape shape, ShapePaint shapePaint, Point point) {
		circ = new Circle(point.getX(),
				point.getY(), SIZE);
		circ.translateXProperty().bind(
				shape.translateXProperty());
		circ.translateYProperty().bind(
				shape.translateYProperty());
		circ.centerXProperty().bind(point.xProperty());
		circ.centerYProperty().bind(point.yProperty());
		setActionHandlers();
		circ.setUserData(shapePaint);
		circ.setId("Resizer" + new Random().nextInt());
		circ.toFront();
		rotationPivot = new Point();
	}
	/**
	 * Adds the resizer to the pane.
	 * @param contentPane The pane on which
	 * the resier will be drawn
	 */
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(circ);
		hide();
	}
	/**
	 * Shows the resizer on the pane.
	 */
	public void show() {
		circ.setVisible(true);
	}
	/**
	 * Hides the resizer from the pane.
	 */
	public void hide() {
		circ.setVisible(false);
	}

	private void setActionHandlers() {
		ShapeController controller =
				new ShapeController();
		controller.addHandlers(circ);
	}
	/**
	 * Gets the x coordinate of the resizer's point
	 * of attachment.
	 * @return The x coordinate of the resizer's point
	 * of attachment
	 */
	public double getX() {
		return circ.centerXProperty().doubleValue();
	}
	/**
	 * Gets the y coordinate of the resizer's point
	 * of attachment.
	 * @return The y coordinate of the resizer's point
	 * of attachment
	 */
	public double getY() {
		return circ.centerYProperty().doubleValue();
	}
	/**
	 * Removes the resizer from the pane
	 * @param contentPane The pane from which
	 * the resizer will be removed.
	 */
	public void remove(Pane contentPane) {
		contentPane.getChildren()
			.remove(circ);
	}
	/**
	 *
	 * @param pivot
	 */
	public void setRotationPivot(Point pivot) {
		rotationPivot.xProperty().bind(
				pivot.xProperty());
		rotationPivot.yProperty().bind(
				pivot.yProperty());
	}
	/**
	 * Rotates the resizer around the
	 * rotation angle by a given value.
	 * @param angle The rotation angle of the resizer
	 */
	public void rotate(double angle) {
		circ.getTransforms().add(new Rotate(angle,
				rotationPivot.getX(), rotationPivot.getY()));
	}
}

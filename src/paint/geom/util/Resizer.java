package paint.geom.util;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import paint.geom.Point;
import paint.geom.ShapePaint;

public class Resizer {
	private static final double SIZE = 4;
	private Circle circ;
	private Point rotationPivot;

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

	public void draw(Pane contentPane) {
		contentPane.getChildren().add(circ);
		hide();
	}

	public void show() {
		circ.setVisible(true);
	}

	public void hide() {
		circ.setVisible(false);
	}

	private void setActionHandlers() {
		ShapeController controller =
				new ShapeController();
		controller.addHandlers(circ);
	}

	public double getX() {
		return circ.centerXProperty().doubleValue();
	}

	public double getY() {
		return circ.centerYProperty().doubleValue();
	}

	public void remove(Pane contentPane) {
		contentPane.getChildren()
			.remove(circ);
	}

	public void setRotationPivot(Point pivot) {
		rotationPivot.xProperty().bind(
				pivot.xProperty());
		rotationPivot.yProperty().bind(
				pivot.yProperty());
	}

	public void rotate(double angle) {
		circ.getTransforms().add(new Rotate(angle,
				rotationPivot.getX(), rotationPivot.getY()));
	}
}

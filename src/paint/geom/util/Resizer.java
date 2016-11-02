package paint.geom.util;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import paint.geom.Point;
import paint.geom.ShapePaint;

public class Resizer {
	private static final double SIZE = 5;
	private Rectangle rect;

	public Resizer(Shape shape, ShapePaint shapePaint, Point point) {
		rect = new Rectangle(point.getX(),
				point.getY(), SIZE, SIZE);
		rect.translateXProperty().bind(
				shape.translateXProperty());
		rect.translateYProperty().bind(
				shape.translateYProperty());
		rect.xProperty().bind(point.xProperty());
		rect.yProperty().bind(point.yProperty());
		setActionHandlers();
		rect.setUserData(shapePaint);
		rect.setId("Resizer" + new Random().nextInt());
		rect.toFront();
		rect.onKeyPressedProperty().bind(
				shape.onKeyPressedProperty());
	}

	public void draw(Pane contentPane) {
		contentPane.getChildren().add(rect);
		hide();
	}

	public void show() {
		rect.setVisible(true);
	}

	public void hide() {
		rect.setVisible(false);
	}

	private void setActionHandlers() {
		ShapeController controller =
				new ShapeController();
		controller.addHandlers(rect);
	}

	public double getX() {
		return rect.xProperty().doubleValue();
	}

	public double getY() {
		return rect.yProperty().doubleValue();
	}

	public void remove(Pane contentPane) {
		contentPane.getChildren()
			.remove(rect);
	}
}

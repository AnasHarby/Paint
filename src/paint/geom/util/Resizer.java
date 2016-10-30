package paint.geom.util;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import paint.geom.Point;

public class Resizer {

	private static final double SIZE = 10;
	private Shape parentShape;

	ArrayList<Rectangle> rects;
	public Resizer(Shape shape, double... positions) {
		rects = new ArrayList<Rectangle>();
		parentShape = shape;
		boolean f = false;
		double temp = 0;
		for (double position : positions) {
			if (!f) {
				temp = position;
			} else {
				rects.add(new Rectangle(temp,
						position, SIZE, SIZE));
			}
			f ^= true;
		}
	}

	public Resizer(Shape shape, Point... positions) {
		rects = new ArrayList<Rectangle>();
		parentShape = shape;
		for (Point position : positions) {
			rects.add(new Rectangle(position.getX(),
					position.getY(), SIZE, SIZE));
		}
	}

	public Resizer(Shape shape, Collection<Double> positions) {
		rects = new ArrayList<Rectangle>();
		parentShape = shape;
		boolean f = false;
		double temp = 0;
		for (double position : positions) {
			if (!f) {
				temp = position;
			} else {
				rects.add(new Rectangle(temp,
						position, SIZE, SIZE));
			}
			f ^= true;
		}
	}

	public void addResizers(Pane contentPane) {
		for (Rectangle rect : rects) {
			rect.translateXProperty().bind(
					parentShape.translateXProperty());
			rect.translateYProperty().bind(
					parentShape.translateYProperty());
			contentPane.getChildren().add(rect);
			rect.toFront();
		}
	}


	public void removeResizers(Pane contentPane) {
		for (Rectangle rect : rects) {
			contentPane.getChildren().remove(rect);
		}
	}
}

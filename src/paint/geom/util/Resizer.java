package paint.geom.util;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import paint.geom.Point;

public class Resizer {

	private static final double SIZE = 5;

	ArrayList<Rectangle> rects;

	public Resizer(double... positions) {
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

	public Resizer(Point... positions) {
		for (Point position : positions) {
			rects.add(new Rectangle(position.getX(),
					position.getY(), SIZE, SIZE));
		}
	}

	public void bind(Shape shape) {
		for (Rectangle rect : rects) {
		}
	}
}

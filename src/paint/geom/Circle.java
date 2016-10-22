package paint.geom;

import java.awt.Point;

public class Circle extends Ellipse {
	public Circle(Point center, double radius) {
		//Default constructor --> Can't do the math.
		super(center, radius, radius);
	}

}

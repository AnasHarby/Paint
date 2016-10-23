package paint.geom;

import java.awt.Point;

public class CirclePaint extends EllipsePaint {
	public CirclePaint(Point center, double radius) {
		//Default constructor --> Can't do the math.
		super(center, radius, radius);
	}
}

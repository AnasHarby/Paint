package paint.geom;

import paint.geom.util.ShapeFactory;

public class SquarePaint extends RectanglePaint {
	public static final String KEY = "square";
	private static final int UPPER_LEFT_X = 0;
	private static final int UPPER_LEFT_Y = 1;
	private static final int BOTTOM_RIGHT_X = 2;
	private static final int BOTTOM_RIGHT_Y = 3;

	static {
		ShapeFactory.getInstance().registerShape(KEY, SquarePaint.class);
	}

	public SquarePaint(Point upperLeft, double length) {
		super(upperLeft, length, length);
	}

	public SquarePaint(double... properties) {
		this(new Point(Math.min(properties[UPPER_LEFT_X],
				properties[BOTTOM_RIGHT_X]),
				Math.min(properties[UPPER_LEFT_Y],
						properties[BOTTOM_RIGHT_Y])),
				Math.abs(properties[UPPER_LEFT_X]
						- properties[BOTTOM_RIGHT_X]));
	}

	@Override
	public String getIconUrl() {
		return null;
	}
	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		double xOpposite = 0;
		double yOpposite = 0;
		for (Point point : super.points) {
			if (x1 != point.getX()
					&& y1 != point.getY()) {
				xOpposite = point.getX();
				yOpposite = point.getY();
			}
		}
		double m = (y1 - yOpposite)
				/ (x1 - xOpposite);
		double x = (m / (m * m + 1)) * (m * x1 + x2 / m + y2 - y1);
		double y = y1 + m * x - m * x1;
		int i = 0;
		for (Point point : super.points) {
			if (x1 == point.getX()
					&& y1 == point.getY()) {
				point.setX(x);
				point.setY(y);
			} else if (x1 == point.getX()) {
				double dy = y1 - y;
				point.setX(x);
				point.setY(point.getY() + dy);
			} else if (y1 == point.getY()) {
				double dx = x1 - x;
				point.setX(point.getX() + dx);
				point.setY(y);
			} else {
				double dx = x1 - x;
				double dy = y1 - y;
				point.setX(point.getX() + dx);
				point.setY(point.getY() + dy);
			}
			super.polygon.getPoints().set(i * 2, point.getX());
			super.polygon.getPoints().set(i * 2 + 1, point.getY());
			i++;
		}
	}
}


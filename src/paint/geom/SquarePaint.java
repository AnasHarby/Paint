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
	public void resize(double x1, double x2, double y1, double y2) {
		for (int i = 0;
				i < super.polygon.getPoints().size(); i += 2) {
			double x = super.polygon.getPoints().get(i);
			double y = super.polygon.getPoints().get(i + 1);
			if (x1 == x && y1 == y) {
				super.polygon.getPoints().set(i, x2);
				super.polygon.getPoints().set(i + 1, y2);
			} else if (x == x1) {
				super.polygon.getPoints().set(i, x2);
			} else if (y == y1) {
				super.polygon.getPoints().set(i + 1, y2);
			}
		}
	}
}


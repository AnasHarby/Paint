package paint.geom;

import paint.geom.util.ShapeFactory;

public class SquarePaint extends RectanglePaint {
	private static final String KEY = "square";

	static {
		ShapeFactory.getInstance().registerShape(KEY, SquarePaint.class);
	}

	public SquarePaint(Point upperLeft, double length) {
		super(upperLeft, length, length);
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


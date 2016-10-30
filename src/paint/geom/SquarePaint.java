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
}

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
}

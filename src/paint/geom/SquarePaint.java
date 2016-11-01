package paint.geom;

import java.util.Random;

import paint.geom.util.ShapeFactory;

public class SquarePaint extends RectanglePaint implements Cloneable {
	public static final String KEY = "square";
	private static final int UPPER_LEFT_X = 0;
	private static final int UPPER_LEFT_Y = 1;
	private static final int BOTTOM_RIGHT_X = 2;
	private static final int BOTTOM_RIGHT_Y = 3;
	private Point upperLeft;
	private double sideLength;
	
	static {
		ShapeFactory.getInstance().registerShape(KEY, SquarePaint.class);
	}
	
	public SquarePaint(Point upperLeft, double length) {
		super(upperLeft, length, length);
		this.upperLeft = upperLeft;
		this.sideLength = length;
		polygon.setId(KEY + new Random().nextInt());
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
	public SquarePaint clone() throws CloneNotSupportedException {
		SquarePaint newObject = new SquarePaint(upperLeft.clone(), sideLength);
		newObject.polygon.setTranslateX(polygon.getTranslateX());
		newObject.polygon.setTranslateY(polygon.getTranslateY());
		newObject.polygon.setRotate(polygon.getRotate());
		newObject.polygon.setFill(polygon.getFill());
		newObject.polygon.setStroke(polygon.getStroke());
		newObject.polygon.setStrokeWidth(polygon.getStrokeWidth());
		return newObject;
	}
}

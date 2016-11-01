package paint.geom;

import java.util.Random;

import paint.geom.util.ShapeFactory;

public class TrianglePaint  extends PolygonPaint implements Cloneable {
	public static final String KEY = "triangle";
	private static final int FIRST_X = 0;
	private static final int FIRST_Y = 1;
	private static final int SECOND_X = 2;
	private static final int SECOND_Y = 3;
	private static final int THIRD_X = 4;
	private static final int THIRD_Y = 5;
	private Point point1;
	private Point point2;
	private Point point3;
	
	static {
		ShapeFactory.getInstance().registerShape(KEY, TrianglePaint.class);
	}
	
	public TrianglePaint(Point point1,
			Point point2, Point point3) {
		super(point1, point2, point3);
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		polygon.setId(KEY + new Random().nextInt());
	}
	
	public TrianglePaint(double... properties) {
		super(properties);
		point1 = new Point(properties[FIRST_X], properties[FIRST_Y]);
		point2 = new Point(properties[SECOND_X], properties[SECOND_Y]);
		point3 = new Point(properties[THIRD_X], properties[THIRD_Y]);
		polygon.setId(KEY + new Random().nextInt());
	}
	
	@Override
	public String getIconUrl() {
		return null;
	}
	
	@Override
	public TrianglePaint clone() throws CloneNotSupportedException {
		TrianglePaint newObject = new TrianglePaint(point1.clone(),
				point2.clone(), point3.clone());
		newObject.polygon.setTranslateX(polygon.getTranslateX());
		newObject.polygon.setTranslateY(polygon.getTranslateY());
		newObject.polygon.setRotate(polygon.getRotate());
		newObject.polygon.setFill(polygon.getFill());
		newObject.polygon.setStroke(polygon.getStroke());
		newObject.polygon.setStrokeWidth(polygon.getStrokeWidth());
		return newObject;
	}

}

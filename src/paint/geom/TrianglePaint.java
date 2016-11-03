package paint.geom;

import java.util.Random;

import javafx.scene.paint.Color;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.ShapeProperties;
import paint.shapes.util.TriangleProperties;

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

	public TrianglePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY(),
				properties.getPoint3().getX(),
				properties.getPoint3().getY());
		polygon.setStroke(properties.getStrokeColor());
		polygon.setFill(properties.getFillColor());
		polygon.setStrokeWidth(properties.getStrokeWidth());
		polygon.setRotate(properties.getRotation());
		polygon.setTranslateX(properties.getTranslateX());
		polygon.setTranslateY(properties.getTranslateY());
		polygon.setId(properties.getId());
	}

	@Override
	public String getIconUrl() {
		return null;
	}

	@Override
	public void setTranslateX(double translateX) {
		polygon.setTranslateX(translateX);
	}

	@Override
	public void setTranslateY(double translateY) {
		polygon.setTranslateY(translateY);
	}

	@Override
	public TrianglePaint clone() throws CloneNotSupportedException {
		TrianglePaint newObject = new TrianglePaint(point1.clone(),
				point2.clone(), point3.clone());
		newObject.polygon.setTranslateX(polygon.getTranslateX());
		newObject.polygon.setTranslateY(polygon.getTranslateY());
		newObject.polygon.setRotate(polygon.getRotate());
		Color col = (Color) polygon.getFill();
		newObject.polygon.setFill(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		col = (Color) polygon.getStroke();
		newObject.polygon.setStroke(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		newObject.polygon.setStrokeWidth(polygon.getStrokeWidth());
		return newObject;
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new TriangleProperties();
		prop.setFillColor(polygon.getFill());
		prop.setStrokeColor(polygon.getStroke());
		try {
			prop.setPoint1(point1.clone());
			prop.setPoint2(point2.clone());
			prop.setPoint3(point3.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setStrokeWidth(polygon.getStrokeWidth());
		prop.setRotation(polygon.getRotate());
		prop.setTranslateX(polygon.getTranslateX());
		prop.setTranslateY(polygon.getTranslateY());
		prop.setId(polygon.getId());
		return prop;
	}
	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		super.resize(x1, y1, x2, y2);
		point1.setX(super.polygon.getPoints().get(FIRST_X));
		point1.setY(super.polygon.getPoints().get(FIRST_Y));
		point2.setX(super.polygon.getPoints().get(SECOND_X));
		point2.setY(super.polygon.getPoints().get(SECOND_Y));
		point3.setX(super.polygon.getPoints().get(THIRD_X));
		point3.setY(super.polygon.getPoints().get(THIRD_Y));
	}

	private Point getCenter() {
		return null;
	}
}

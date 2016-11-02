package paint.shapes.util;

import javafx.scene.paint.Paint;
import paint.geom.Point;

public abstract class ShapeProperties {
	protected Point point1;
	protected Point point2;
	protected Point point3;
	protected double translateX;
	protected double translateY;
	protected double rotation;
	protected double StrokeWidth;
	protected Paint strokeColor;
	protected Paint fillColor;
	protected String key;

	public Point getPoint1() {
		return point1;
	}
	public void setPoint1(Point point1) {
		this.point1 = point1;
	}
	public Point getPoint2() {
		return point2;
	}
	public void setPoint2(Point point2) {
		this.point2 = point2;
	}
	public abstract Point getPoint3();
	public abstract void setPoint3(Point point3);

	public double getTranslateX() {
		return translateX;
	}
	public void setTranslateX(double translateX) {
		this.translateX = translateX;
	}
	public double getTranslateY() {
		return translateY;
	}
	public void setTranslateY(double translateY) {
		this.translateY = translateY;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotationX) {
		this.rotation = rotationX;
	}

	public double getStrokeWidth() {
		return StrokeWidth;
	}
	public void setStrokeWidth(double strokeWidth) {
		StrokeWidth = strokeWidth;
	}
	public Paint getStrokeColor() {
		return strokeColor;
	}
	public void setStrokeColor(Paint strokeColor) {
		this.strokeColor = strokeColor;
	}
	public Paint getFillColor() {
		return fillColor;
	}
	public void setFillColor(Paint paint) {
		this.fillColor = paint;
	}
	public String getKey() {
		return key;
	}
}

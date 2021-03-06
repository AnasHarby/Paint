package paint.geom;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import paint.geom.prop.LineProperties;
import paint.geom.prop.ShapeProperties;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;

/**
 * The Line class creates a new line
 * with the specified two ends locations in pixels.
 * <br>
 * Implements {@link paint.geom.ShapePaint} interface
 * and {@link Cloneable} interface.
 */
public class LinePaint implements ShapePaint, Cloneable {

	/**
	 * The key of the line class, used for getting
	 * {@link ShapePaint} instances from {@link ShapeFactory}.
	 */
	public static final String KEY = "line";
	private static final int FIRST_X = 0;
	private static final int FIRST_Y = 1;
	private static final int SECOND_X = 2;
	private static final int SECOND_Y = 3;
	private Line line;
	private ArrayList<Resizer> resizers;
	private Point start;
	private Point end;
	private Point center;
	private double rotation = 0;

	static {
		ShapeFactory.getInstance().registerShape(KEY, LinePaint.class);
	}

	/**
	 * Default constructor for construction of {@link LinePaint}.
	 * @param point1 {@link Point} representing the start of the line segment.
	 * @param point2 {@link Point} representing the end of the line segment.
	 */
	public LinePaint(Point point1, Point point2) {
		line = new Line(point1.getX(), point1.getY(),
				point2.getX(), point2.getY());
		resizers = new ArrayList<Resizer>();
		this.start = point1;
		this.end = point2;
		center = new Point();
		setFill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
		line.setId(KEY + new Random().nextInt());
		line.setUserData(this);
	}

	/**
	 * Default constructor for construction of {@link LinePaint}
	 * by using an equivalent
	 * <br>
	 * {@link ShapeProperties} object containing all specifications of the line.
	 * @param properties {@link ShapeProperties} object.
	 */
	public LinePaint(double... properties) {
		this(new Point(properties[FIRST_X], properties[FIRST_Y]),
				new Point(properties[SECOND_X], properties[SECOND_Y]));
	}

	/**
	 * Default constructor for construction of {@link LinePaint}
	 * by using a collection
	 * <br>
	 * of double values representing the two ends of line.
	 * @param properties properties of {@link LinePaint}
	 */
	public LinePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY());
		line.setStroke(properties.getStrokeColor());
		line.setFill(properties.getFillColor());
		line.setStrokeWidth(properties.getStrokeWidth());
		line.setTranslateX(properties.getTranslateX());
		line.setTranslateY(properties.getTranslateY());
		line.setId(properties.getId());
		rotate(properties.getRotation());
	}

	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(line);
		for (Resizer resizer : resizers) {
			resizer.draw(contentPane);
		}
	}

	@Override
	public void setBorderColor(Color col) {
		line.setStroke(col);
	}

	@Override
	public void setFill(Color col) {
		line.setFill(col);
	}

	@Override
	public Color getFill() {
		return (Color) line.getFill();
	}

	@Override
	public void setBorderWidth(double width) {
		line.setStrokeWidth(width);
	}

	@Override
	public void move(double x, double y) {
		line.setTranslateX(x);
		line.setTranslateY(y);
	}

	@Override
	public void toBack() {
		line.toBack();
	}

	@Override
	public void toFront() {
		line.toFront();
	}

	@Override
	public void remove(Pane contentPane) {
		contentPane.getChildren().remove(line);
		for (Resizer resizer : resizers) {
			resizer.remove(contentPane);
		}
	}

	@Override
	public void setTranslateX(double translateX) {
		line.setTranslateX(translateX);
	}

	@Override
	public void setTranslateY(double translateY) {
		line.setTranslateY(translateY);
	}

	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		double m = (start.getY() - end.getY())
				/ (start.getX() - end.getX());
		double x = (m / (m * m + 1)) * (m * x1 + x2 / m + y2 - y1);
		double y = y1 + m * x - m * x1;
		if (x1 == start.getX()
				&& y1 == start.getY()) {
			start.setX(x);
			start.setY(y);
		} else if (x1 == end.getX()
				&& y1 == end.getY()) {
			end.setX(x);
			end.setY(y);
		}
		line.setStartX(start.getX());
		line.setStartY(start.getY());
		line.setEndX(end.getX());
		line.setEndY(end.getY());
	}

	@Override
	public void showResizers() {
		for (Resizer resizer : resizers) {
			resizer.show();
		}
	}

	@Override
	public void hideResizers() {
		for (Resizer resizer : resizers) {
			resizer.hide();
		}
	}

	@Override
	public void setOnMouseClicked(EventHandler<MouseEvent> handler) {
		line.setOnMouseClicked(handler);
	}

	@Override
	public void setOnMousePressed(EventHandler<MouseEvent> handler) {
		line.setOnMousePressed(handler);
	}

	@Override
	public void setOnMouseDragged(EventHandler<MouseEvent> handler) {
		line.setOnMouseDragged(handler);
	}

	@Override
	public void setOnMouseReleased(EventHandler<MouseEvent> handler) {
		line.setOnMouseReleased(handler);
	}

	@Override
	public LinePaint clone() throws CloneNotSupportedException {
		LinePaint newObject = new LinePaint(start.clone(), end.clone());
		newObject.line.setTranslateX(line.getTranslateX());
		newObject.line.setTranslateY(line.getTranslateY());
		newObject.line.setRotate(line.getRotate());
		Color col = (Color) line.getFill();
		newObject.line.setFill(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		col = (Color) line.getStroke();
		newObject.line.setStroke(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		newObject.line.setStrokeWidth(line.getStrokeWidth());
		newObject.line.setOnMouseClicked(line.getOnMouseClicked());
		newObject.rotate(rotation);
		return newObject;
	}

	@Override
	public String getId() {
		return line.getId();
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new LineProperties();
		prop.setFillColor(line.getFill());
		prop.setStrokeColor(line.getStroke());
		try {
			prop.setPoint1(start.clone());
			prop.setPoint2(end.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		prop.setStrokeWidth(line.getStrokeWidth());
		prop.setTranslateX(line.getTranslateX());
		prop.setTranslateY(line.getTranslateY());
		prop.setId(line.getId());
		prop.setRotation(rotation);
		return prop;
	}

	@Override
	public void rotate(double angle) {
		rotation += angle;
		line.getTransforms().add(new Rotate(
				angle, getCenter().getX(),
				getCenter().getY()));
		for (Resizer resizer : resizers) {
			resizer.rotate(angle);
		}
	}

	private Point getCenter() {
		double x = (line.getStartX()
				+ line.getEndX()) / 2;
		double y = (line.getStartY()
				+ line.getEndY()) / 2;
		center.setX(x);
		center.setY(y);
		return center;
	}

	private void setResizers() {
		resizers.add(new Resizer(line, this, start));
		resizers.add(new Resizer(line, this, end));
		for (Resizer resizer : resizers) {
			resizer.setRotationPivot(getCenter());
		}
	}

	private void setActionHandlers() {
		ShapeController shapeMovement
		= new ShapeController();
		shapeMovement.addHandlers(line);
	}
}

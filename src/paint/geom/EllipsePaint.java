package paint.geom;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.EllipseProperties;
import paint.shapes.util.ShapeProperties;

public class EllipsePaint implements ShapePaint, Cloneable {
	/**
	 * Javafx 2D graphics drawing ellipse
	 * class.
	 */
	public static final String KEY = "ellipse";
	public Ellipse ellipse;
	private Point up;
	private Point down;
	private Point left;
	private Point right;
	private ArrayList<Resizer> resizers;
	private static final int TOPMOST_X = 0;
	private static final int TOPMOST_Y = 1;
	private static final int RIGHTMOST_X = 2;
	private static final int RIGHTMOST_Y = 3;

	static {
		ShapeFactory.getInstance().registerShape(KEY, EllipsePaint.class);
	}

	public EllipsePaint(Point center, double a, double b) {
		ellipse = new Ellipse(center.getX(),
				center.getY(), a, b);
		resizers = new ArrayList<Resizer>();
		up = new Point();
		down = new Point();
		left = new Point();
		right = new Point();
		setVertices();
		setResizers();
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		ellipse.setId(KEY + new Random().nextInt());
		ellipse.setUserData(this);
	}

	public EllipsePaint(ShapeProperties properties) {
		this(properties.getPoint1().getX(),
				properties.getPoint1().getY(),
				properties.getPoint2().getX(),
				properties.getPoint2().getY());
		ellipse.setStroke(properties.getStrokeColor());
		ellipse.setFill(properties.getFillColor());
		ellipse.setStrokeWidth(properties.getStrokeWidth());
		ellipse.setRotate(properties.getRotation());
		ellipse.setTranslateX(properties.getTranslateX());
		ellipse.setTranslateY(properties.getTranslateY());
	}

	public EllipsePaint(double... properties) {
		this(new Point(properties[TOPMOST_X], properties[RIGHTMOST_Y]),
				Math.abs(properties[RIGHTMOST_X] - properties[TOPMOST_X]),
				Math.abs(properties[TOPMOST_Y] - properties[RIGHTMOST_Y]));
	}

	public void rotate(double angle) {
		ellipse.setRotate(angle);
	}

	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(ellipse);
		for (Resizer resizer : resizers) {
			resizer.draw(contentPane);
		}
	}

	@Override
	public void remove(Pane contentPane) {
		contentPane.getChildren().remove(ellipse);
	}

	@Override
	public void fill(Color col) {
		ellipse.setFill(col);
	}

	@Override
	public void setBorderColor(Color col) {
		ellipse.setStroke(col);
	}

	@Override
	public String getIconUrl() {
		return null;
	}

	@Override
	public void setBorderWidth(double width) {
		ellipse.setStrokeWidth(width);
	}

	@Override
	public void move(double x, double y) {
		ellipse.setTranslateX(x);
		ellipse.setTranslateY(y);
	}

	@Override
	public void toBack() {
		ellipse.toBack();
	}

	@Override
	public void toFront() {
		ellipse.toFront();
	}
	private void setActionHandlers() {
		ShapeController shapeMovement
		= new ShapeController();
		shapeMovement.addHandlers(ellipse);
	}
	@Override
	public void resize(double x1,
			double y1, double x2, double y2) {
		Point point = new Point(x1, y1);
		double x = ellipse.getRadiusX();
		double y = ellipse.getRadiusY();
		if (point.equals(up)) {
			double dy = y1 - y2;
			if (y + dy > 0) {
				ellipse.setRadiusY(y + dy);
			}
		} else if (point.equals(down)) {
			double dy = y2 - y1;
			if (y + dy > 0) {
				ellipse.setRadiusY(y + dy);
			}
		} else if (point.equals(left)) {
			double dx = x1 - x2;
			if (x + dx > 0) {
				ellipse.setRadiusX(x + dx);
			}
		} else if (point.equals(right)) {
			double dx = x2 - x1;
			if (x + dx > 0) {
				ellipse.setRadiusX(x + dx);
			}
		}
		setVertices();
	}
	protected void setVertices() {
		up.setX(ellipse.getCenterX());
		up.setY(ellipse.getCenterY()
				- ellipse.getRadiusY());
		left.setX(ellipse.getCenterX()
				- ellipse.getRadiusX());
		left.setY(ellipse.getCenterY());
		down.setX(ellipse.getCenterX());
		down.setY(ellipse.getCenterY()
				+ ellipse.getRadiusY());
		right.setX(ellipse.getCenterX()
				+ ellipse.getRadiusX());
		right.setY(ellipse.getCenterY());
	}

	private void setResizers() {
		resizers.add(new Resizer(ellipse, this, up));
		resizers.add(new Resizer(ellipse, this, left));
		resizers.add(new Resizer(ellipse, this, down));
		resizers.add(new Resizer(ellipse, this, right));
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
	public void setTranslateX(double translateX) {
		ellipse.setTranslateX(translateX);
	}

	@Override
	public void setTranslateY(double translateY) {
		ellipse.setTranslateY(translateY);
	}

	@Override
	public EllipsePaint clone() throws CloneNotSupportedException {
		double radX = ellipse.getCenterX();
		double radY = ellipse.getCenterY();
		EllipsePaint newObject = new EllipsePaint(
				new Point(radX, radY), ellipse.getRadiusX(),
					ellipse.getRadiusY());
		newObject.ellipse.setTranslateX(ellipse.getTranslateX());
		newObject.ellipse.setTranslateY(ellipse.getTranslateY());
		newObject.ellipse.setRotate(ellipse.getRotate());
		Color col = (Color) ellipse.getFill();
		newObject.ellipse.setFill(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		col = (Color) ellipse.getStroke();
		newObject.ellipse.setStroke(new Color(col.getRed(), col.getGreen(),
				col.getBlue(), col.getOpacity()));
		newObject.ellipse.setStrokeWidth(ellipse.getStrokeWidth());
		return newObject;
	}

	@Override
	public String getId() {
		return ellipse.getId();
	}

	@Override
	public ShapeProperties getShapeProperties() {
		ShapeProperties prop = new EllipseProperties();
		prop.setFillColor(ellipse.getFill());
		prop.setStrokeColor(ellipse.getStroke());
		try {
			prop.setPoint1(up.clone());
			prop.setPoint2(right.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		prop.setStrokeWidth(ellipse.getStrokeWidth());
		prop.setRotation(ellipse.getRotate());
		prop.setTranslateX(ellipse.getTranslateX());
		prop.setTranslateY(ellipse.getTranslateY());
		return prop;
	}

	@Override
	public void setOnMouseClicked(EventHandler<MouseEvent> handler) {
		ellipse.setOnMouseClicked(handler);
	}
}

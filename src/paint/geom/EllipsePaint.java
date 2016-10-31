package paint.geom;


import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;

public class EllipsePaint implements ShapePaint {
	/**
	 * Javafx 2D graphics drawing ellipse
	 * class.
	 */
	public static final String KEY = "ellipse";
	protected Ellipse ellipse;
	private Point centerEllipse;
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
		centerEllipse = center;
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
		double aEllipse = ellipse.getRadiusX();
		double bEllipse = ellipse.getRadiusY();
		if (point.equals(up)) {
			double dy = y1 - y2;
			if (bEllipse + dy > 0) {
				bEllipse += dy;
			}
		} else if (point.equals(down)) {
			double dy = y2 - y1;
			if (bEllipse + dy > 0) {
				bEllipse += dy;
			}
		} else if (point.equals(left)) {
			double dx = x1 - x2;
			if (aEllipse + dx > 0) {
				aEllipse += dx;
			}
		} else if (point.equals(right)) {
			double dx = x2 - x1;
			if (aEllipse + dx > 0) {
				aEllipse += dx;
			}
		}
		ellipse.setRadiusX(aEllipse);
		ellipse.setRadiusY(bEllipse);
		setVertices();
	}
	protected void setVertices() {
		up.setX(centerEllipse.getX());
		up.setY(centerEllipse.getY() - ellipse.getRadiusY());
		left.setX(centerEllipse.getX() - ellipse.getRadiusX());
		left.setY(centerEllipse.getY());
		down.setX(centerEllipse.getX());
		down.setY(centerEllipse.getY() + ellipse.getRadiusY());
		right.setX(centerEllipse.getX() + ellipse.getRadiusX());
		right.setY(centerEllipse.getY());
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
}

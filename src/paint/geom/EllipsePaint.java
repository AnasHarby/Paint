package paint.geom;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;

public class EllipsePaint implements ShapePaint {
	/**
	 * Javafx 2D graphics drawing ellipse
	 * class.
	 */
	public Ellipse ellipse;
	private double aEllipse;
	private double bEllipse;
	private Point centerEllipse;
	private Point up;
	private Point down;
	private Point left;
	private Point right;
	private static final String KEY = "ellipse";
	
	static {
		ShapeFactory.getInstance().registerShape(KEY, EllipsePaint.class);
	}
	

	public EllipsePaint(Point center, double a, double b) {
		centerEllipse = center;
		aEllipse = a;
		bEllipse = b;
		ellipse = new Ellipse(center.getX(),
				center.getY(), a, b);
		setVertices();
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
	}

	public void rotate(double angle) {
		ellipse.setRotate(angle);
	}

	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(ellipse);
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
		if (point.equals(up)
				|| point.equals(down)) {
			bEllipse += y2 - y1;
		} else if (point.equals(left)
				|| point.equals(right)) {
			aEllipse += x2 - x1;
		}
		setVertices();
		ellipse.setRadiusX(aEllipse);
		ellipse.setRadiusY(bEllipse);
	}
	private void setVertices() {
		up = new Point(
				centerEllipse.getX(),
				centerEllipse.getY() - bEllipse);
		left = new Point(
				centerEllipse.getX() - aEllipse,
				centerEllipse.getY());
		down = new Point(
				centerEllipse.getX(),
				centerEllipse.getY() + bEllipse);
		right = new Point(
				centerEllipse.getX() + aEllipse,
				centerEllipse.getY());
	}
}

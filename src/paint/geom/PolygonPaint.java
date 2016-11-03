package paint.geom;

import java.util.ArrayList;
import java.util.Collection;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;

/**
 * Abstract {@link PolygonPaint} class allows any form of geometric polygon
 * shape to extend its properties and methods.
 * <br>
 * Implements {@link paint.geom.ShapePaint} interface
 * and {@link Cloneable} interface.
 *
 */
public abstract class PolygonPaint implements ShapePaint {

	 /**
	 * The equivalent {@link javafx.scene.shape.Polygon}
	 * used for interacting with the {@link javafx} library.
	 */
	protected Polygon polygon;

	/**
	 * Array for accessing the {@link Resizer} type nodes on polygon.
	 */
	protected ArrayList<Resizer> resizers;

	/**
	 * Array for accessing points representing vertices of polygon.
	 */
	protected ArrayList<Point> points;

	/**
	 * Default constructor for construction of {@link PolygonPaint}.
	 * @param vertices collection of vertices of polygon.
	 */
	public PolygonPaint(Point... vertices) {
		polygon = new Polygon();
		resizers = new ArrayList<Resizer>();
		points = new ArrayList<Point>();
		for (Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
			points.add(vertex);
		}
		setFill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
		polygon.setUserData(this);
	}

	/**
	 * Default constructor for construction of {@link PolygonPaint}.
	 * @param vertices double values of (x,y) for each vertex of polygon.
	 */
	public PolygonPaint(double... vertices) {
		polygon = new Polygon(vertices);
		resizers = new ArrayList<Resizer>();
		points = new ArrayList<Point>();
		double temp = 0;
		boolean f = false;
		for (double vertex : vertices) {
			if (!f) {
				temp = vertex;
			} else {
				points.add(new Point(temp, vertex));
			}
			f ^= true;
		}
		setFill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
		polygon.setUserData(this);
	}

	/**
	 * Default constructor for construction of {@link PolygonPaint}.
	 * @param vertices Collection of {@link Point} type vertices.
	 */
	public PolygonPaint(Collection<Point> vertices) {
		polygon = new Polygon();
		resizers = new ArrayList<Resizer>();
		points = (ArrayList<Point>) vertices;
		for (Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
		}
		setFill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
		polygon.setUserData(this);
	}

	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(polygon);
		for (Resizer resizer : resizers) {
			resizer.draw(contentPane);
		}
	}

	@Override
	public void remove(Pane contentPane) {
		contentPane.getChildren().remove(polygon);
		for (Resizer resizer : resizers) {
			resizer.remove(contentPane);
		}
	}

	@Override
	public void setFill(Color col) {
		polygon.setFill(col);
	}

	@Override
	public Color getFill() {
		return (Color) polygon.getFill();
	}

	@Override
	public void setBorderColor(Color col) {
		polygon.setStroke(col);
	}

	@Override
	public void setBorderWidth(double width) {
		polygon.setStrokeWidth(width);
	}

	@Override
	public void move(double x, double y) {
		polygon.setTranslateX(x);
		polygon.setTranslateY(y);
	}

	@Override
	public void toBack() {
		polygon.toBack();
	}

	@Override
	public void toFront() {
		polygon.toFront();
	}

	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		int i = 0;
		for (Point point : points) {
			if (x1 == point.getX()
					&& y1 == point.getY()) {
				point.setX(x2);
				point.setY(y2);
			}
			polygon.getPoints().set(i * 2, point.getX());
			polygon.getPoints().set(i * 2 + 1, point.getY());
			i++;
		}
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
		polygon.setOnMouseClicked(handler);
	}

	@Override
	public void setOnMousePressed(EventHandler<MouseEvent> handler) {
		polygon.setOnMousePressed(handler);
	}

	@Override
	public void setOnMouseDragged(EventHandler<MouseEvent> handler) {
		polygon.setOnMouseDragged(handler);
	}

	@Override
	public void setOnMouseReleased(EventHandler<MouseEvent> handler) {
		polygon.setOnMouseReleased(handler);
	}

	@Override
	public abstract ShapePaint clone() throws CloneNotSupportedException;

	@Override
	public String getId() {
		return polygon.getId();
	}

	private void setResizers() {
		for (Point point : points) {
			resizers.add(new Resizer(
					polygon, this, point));
		}
	}

	private void setActionHandlers() {
		ShapeController shapeMovement
		= new ShapeController();
		shapeMovement.addHandlers(polygon);
	}
}

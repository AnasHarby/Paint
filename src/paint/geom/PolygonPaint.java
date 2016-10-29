package paint.geom;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import paint.geom.util.ShapeController;

public abstract class PolygonPaint implements ShapePaint {
	public Polygon polygon;
	public ArrayList<Point> polygonVertices;

	public PolygonPaint(Point... vertices) {
		polygon = new Polygon();
		polygonVertices = new ArrayList<Point>();
		for(Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
			polygonVertices.add(vertex);
		}
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
	}

	public PolygonPaint(double... vertices) {
		polygon = new Polygon(vertices);
		int counter = 0;
		double temp = 0;
		for (double vertex : vertices) {
			if (counter == 0) {
				temp = vertex;
			} else {
				polygonVertices.add(new Point(temp, vertex));
			}
			counter ^= 1;
		}
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
	}

	public PolygonPaint(Collection<Point> vertices) {
		polygon = new Polygon();
		for(Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
		}
		polygonVertices = (ArrayList<Point>) vertices;
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
	}

	public void rotate(double angle) {
		polygon.setRotate(angle);
	}

	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(polygon);
	}

	@Override
	public void remove(Pane contentPane) {
		contentPane.getChildren().remove(polygon);
	}

	@Override
	public void fill(Color col) {
		polygon.setFill(col);
	}

	@Override
	public void setBorderColor(Color col) {
		polygon.setStroke(col);
	}

	@Override
	public String getIconUrl() {
		return null;
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
	public void setActionHandlers() {
		ShapeController shapeMovement
		= new ShapeController();
		shapeMovement.addHandlers(polygon);
	}
	@Override
	public void resize(double x1, double x2, double y1, double y2) {
		Polygon newPolygon = new Polygon();
		for (Point vertix : polygonVertices) {
			if (vertix.getX() == x1 && vertix.getY() == x2) {
				vertix.setX(x2);
				vertix.setY(x2);
			}
			newPolygon.getPoints().add(vertix.getX());
			newPolygon.getPoints().add(vertix.getY());
		}
		polygon = newPolygon;
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
	}
}

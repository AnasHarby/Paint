package paint.geom;

import java.util.Collection;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import paint.geom.util.ShapeMovement;

public abstract class PolygonPaint implements ShapePaint {
	Polygon polygon;

	public PolygonPaint(Point... vertices) {
		polygon = new Polygon();
		for(Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
		}
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
	}
	
	public PolygonPaint(double... vertices) {
		polygon = new Polygon(vertices);
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
	private void setActionHandlers() {
		ShapeMovement shapeMovement
		= new ShapeMovement();
		shapeMovement.addHandlers(polygon);
	}
}

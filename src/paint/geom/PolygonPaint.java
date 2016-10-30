package paint.geom;

import java.util.Collection;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;

public abstract class PolygonPaint implements ShapePaint {
	public Polygon polygon;
	private Resizer resizer;

	public PolygonPaint(Point... vertices) {
		polygon = new Polygon();
		for (Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
		}
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
	}

	public PolygonPaint(double... vertices) {
		polygon = new Polygon(vertices);
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
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
		setResizers();
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
		for (int i = 0;
				i < polygon.getPoints().size() - 1; i += 2) {
			double x = polygon.getPoints().get(i);
			double y = polygon.getPoints().get(i + 1);
			if (x == x1 && y == y1) {
				polygon.getPoints().set(i, x2);
				polygon.getPoints().set(i + 1, y2);
			}
		}
	}

	private void setResizers() {
		resizer = new Resizer(polygon,
				polygon.getPoints());
	}

	@Override
	public void showResizers(Pane contentPane) {
		resizer.addResizers(contentPane);
	}

	@Override
	public void hideResizers(Pane contentPane) {
		resizer.removeResizers(contentPane);
	}
}

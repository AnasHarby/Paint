package paint.geom;

import java.util.Collection;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PolygonPaint implements ShapePaint {
	Polygon polygon;
	
	public PolygonPaint(Point... vertices) {
		polygon = new Polygon();
		for(Point vertex : vertices) {
			polygon.getPoints().add(vertex.getX());
			polygon.getPoints().add(vertex.getY());
		}
		fill(Color.WHITE);
		setColor(Color.BLACK);
	}
	
	public PolygonPaint(double... vertices) {
		polygon = new Polygon(vertices);
		fill(Color.WHITE);
		setColor(Color.BLACK);
	}
	
	public PolygonPaint(Collection<Double> vertices) {
		polygon = new Polygon();
		polygon.getPoints().addAll(vertices);
		fill(Color.WHITE);
		setColor(Color.BLACK);
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
	public void setColor(Color col) {
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
}

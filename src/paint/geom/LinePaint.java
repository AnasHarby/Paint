package paint.geom;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LinePaint implements ShapePaint {
	Line line;
	public LinePaint(Point point1, Point point2) {
		line = new Line(point1.getX(), point1.getY(),
				point2.getX(), point2.getY());
		fill(Color.TRANSPARENT);
		setColor(Color.BLACK);
	}
	@Override
	public String getIconUrl() {
		return null;
	}
	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(line);		
	}
	@Override
	public void setColor(Color col) {
		line.setStroke(col);		
	}
	@Override
	public void fill(Color col) {
		line.setFill(col);
	}
	@Override
	public void setBorderWidth(double width) {
		line.setStrokeWidth(width);
	}
}

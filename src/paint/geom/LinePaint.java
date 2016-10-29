package paint.geom;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import paint.geom.util.ShapeController;

public class LinePaint implements ShapePaint {
	private Line line;

	public LinePaint(Point point1, Point point2) {
		line = new Line(point1.getX(), point1.getY(),
				point2.getX(), point2.getY());
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
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
	public void setBorderColor(Color col) {
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
	private void setActionHandlers() {
		ShapeController shapeMovement
		= new ShapeController();
		shapeMovement.addHandlers(line);
	}

	@Override
	public void remove(Pane contentPane) {
		contentPane.getChildren().remove(line);
	}

	@Override
	public void resize(double x1, double y1, double x2, double y2) {
		double m = (line.getStartY() - line.getEndY())
				/ (line.getStartX() - line.getEndX());
		double x = (m / (m * m + 1)) * (m * x1 + x2 / m + y2 - y1);
		double y = y1 + m * x - m * x1;
		if (x1 == line.getStartX()
				&& y1 == line.getStartY()) {
			line.setStartX(x);
			line.setStartY(y);
		} else if (x1 == line.getEndX()
				&& y1 == line.getEndY()) {
			line.setEndX(x);
			line.setEndY(y);
		}
	}
}

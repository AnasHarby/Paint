package paint.geom;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;

public class LinePaint implements ShapePaint {
	private Line line;
	private ArrayList<Resizer> resizers;
	private Point start;
	private Point end;
	private static final String KEY = "line";

	static {
		ShapeFactory.getInstance().registerShape(KEY, LinePaint.class);
	}

	public LinePaint(Point point1, Point point2) {
		line = new Line(point1.getX(), point1.getY(),
				point2.getX(), point2.getY());
		start = point1;
		end = point2;
		resizers = new ArrayList<Resizer>();
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
	}

	@Override
	public String getIconUrl() {
		return null;
	}

	@Override
	public void draw(Pane contentPane) {
		contentPane.getChildren().add(line);
		for (Resizer resizer : resizers) {
			resizer.draw(contentPane);
		}
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
		double m = (start.getY() - end.getY())
				/ (start.getX() - end.getX());
		double x = (m / (m * m + 1)) * (m * x1 + x2 / m + y2 - y1);
		double y = y1 + m * x - m * x1;
		if (x1 == start.getX()
				&& y1 == start.getY()) {
			start.setX(x);
			start.setY(y);
		} else if (x1 == line.getEndX()
				&& y1 == line.getEndY()) {
			end.setX(x);
			end.setY(y);
		}
		line.setStartX(start.getX());
		line.setStartY(start.getY());
		line.setEndX(end.getX());
		line.setEndY(end.getY());
	}

	private void setResizers() {
		resizers.add(new Resizer(line, this, start));
		resizers.add(new Resizer(line, this, end));
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

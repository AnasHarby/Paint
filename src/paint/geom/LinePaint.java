package paint.geom;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import paint.geom.util.Resizer;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;

public class LinePaint implements ShapePaint, Cloneable {
	private Line line;
	private ArrayList<Resizer> resizers;
	public static final String KEY = "line";
	private static final int FIRST_X = 0;
	private static final int FIRST_Y = 1;
	private static final int SECOND_X = 2;
	private static final int SECOND_Y = 3;
	private Point point1;
	private Point point2;
	static {
		ShapeFactory.getInstance().registerShape(KEY, LinePaint.class);
	}

	public LinePaint(Point point1, Point point2) {
		line = new Line(point1.getX(), point1.getY(),
				point2.getX(), point2.getY());
		resizers = new ArrayList<Resizer>();
		fill(Color.TRANSPARENT);
		setBorderColor(Color.BLACK);
		setActionHandlers();
		setResizers();
		this.point1 = point1;
		this.point2 = point2;
		line.setId(KEY + new Random().nextInt());
	}
	
	public LinePaint(double... properties) {
		this(new Point(properties[FIRST_X], properties[FIRST_Y]),
				new Point(properties[SECOND_X], properties[SECOND_Y]));
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
			point1 = new Point(x, y);
		} else if (x1 == line.getEndX()
				&& y1 == line.getEndY()) {
			line.setEndX(x);
			line.setEndY(y);
			point2 = new Point(x, y);
		}
	}

	private void setResizers() {
		resizers.add(new Resizer(line, this, new Point(
				line.getStartX(), line.getStartY())));
		resizers.add(new Resizer(line, this, new Point(
				line.getEndX(), line.getEndY())));
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
	public LinePaint clone() throws CloneNotSupportedException {
		LinePaint newObject = new LinePaint(point1.clone(), point2.clone());
		newObject.line.setTranslateX(line.getTranslateX());
		newObject.line.setTranslateY(line.getTranslateY());
		newObject.line.setRotate(line.getRotate());
		newObject.line.setFill(line.getFill());
		newObject.line.setStroke(line.getStroke());
		newObject.line.setStrokeWidth(line.getStrokeWidth());
		return newObject;
	}

	@Override
	public String getId() {
		return line.getId();
	}

}

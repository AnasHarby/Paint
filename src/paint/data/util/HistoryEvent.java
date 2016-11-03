package paint.data.util;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import paint.geom.ShapePaint;
import paint.geom.util.ShapeFactory;
import paint.shapes.util.ShapeProperties;

public class HistoryEvent implements Cloneable {
	private static final String TRIANGLE_KEY = "triangle";
	private ArrayList<ShapePaint> shapes;

	public HistoryEvent() {
		shapes = new ArrayList<>();
	}

	public HistoryEvent(Collection<ShapeProperties> shapesProperties) {
		shapes = new ArrayList<>();
		for (ShapeProperties prop : shapesProperties) {
			ShapePaint shape;
			if (prop.getKey().equals(TRIANGLE_KEY)) {
				shape = ShapeFactory.getInstance().createShape(prop.getKey(),
						prop.getPoint1().getX(), prop.getPoint1().getY(),
						prop.getPoint2().getX(), prop.getPoint2().getY(),
						prop.getPoint3().getX(), prop.getPoint3().getY());
			} else {
				shape = ShapeFactory.getInstance().createShape(prop.getKey(),
						prop.getPoint1().getX(), prop.getPoint1().getY(),
						prop.getPoint2().getX(), prop.getPoint2().getY());
			}
			shapes.add(shape);
		}
	}

	public void showEvent(Canvas canvas) {
		Pane pane = (Pane) canvas.getParent();
		pane.getChildren().clear();
		pane.getChildren().add(canvas);
		for (ShapePaint shape : shapes) {
			shape.draw(pane);
			shape.showResizers();
			shape.toBack();
		}
	}

	public Collection<ShapePaint> getShapes() {
		return shapes;
	}

	public void updateHistory() {
		History.getHistory().storeShapeChanges(this);
	}

	public Collection<ShapeProperties> getShapesProperties() {
		ArrayList<ShapeProperties> prop = new ArrayList<>();
		for (ShapePaint shape : shapes) {
			prop.add(shape.getShapeProperties());
		}
		return prop;
	}

	public void removeShape(String id) {
		for (int i = 0 ; i < shapes.size() ; i++) {
			if (shapes.get(i).getId().equals(id)) {
				shapes.remove(i);
				return;
			}
		}
	}

	@Override
	public HistoryEvent clone() throws CloneNotSupportedException {
		HistoryEvent clone = new HistoryEvent();
		for (ShapePaint shape : shapes) {
			clone.getShapes().add(shape.clone());
		}
		return clone;
	}
}

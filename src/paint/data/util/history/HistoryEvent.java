package paint.data.util.history;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import paint.geom.ShapePaint;
import paint.geom.prop.ShapeProperties;
import paint.geom.util.ShapeFactory;

/**
 * The Class that represents the current
 * state of the application.
 */
public class HistoryEvent implements Cloneable {

	/** A list of all the shapes in this state. */
	private ArrayList<ShapePaint> shapes;

	/**
	 * Instantiates a new history event.
	 */
	public HistoryEvent() {
		shapes = new ArrayList<>();
	}

	/**
	 * Instantiates a new history event.
	 * @param shapesProperties a collection of
	 * shape properties representing the state
	 * of every shape
	 */
	public HistoryEvent(Collection<ShapeProperties> shapesProperties) {
		shapes = new ArrayList<>();
		for (ShapeProperties prop : shapesProperties) {
			ShapePaint shape
			= ShapeFactory.getInstance().
			createShape(prop.getKey(), prop);
			shapes.add(shape);
		}
	}

	/**
	 * Shows this state on the canvas.
	 * @param canvas the canvas on which
	 * the state will be drawn
	 */
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

	/**
	 * Gets a collection of all
	 * the shapes in this state.
	 * @return the shapes collection
	 */
	public Collection<ShapePaint> getShapes() {
		return shapes;
	}

	/**
	 * Finds a shape in the
	 * current history state by ID.
	 * @param id Shape ID.
	 * @return Shape with the same ID.
	 */
	public ShapePaint getShape(String id)  {
		for (ShapePaint shape : shapes) {
			
			if (shape.getId() != null
					&& shape.getId().equals(id)) {
				return shape;
			}
		}
		return null;
	}

	/**
	 * Adds the current state to the history.
	 */
	public void updateHistory() {
		History.getHistory().storeShapeChanges(this);
	}

	/**
	 * Gets a collection of the properties
	 * of every shape in the current state.
	 * @return the shapes properties collection
	 */
	public Collection<ShapeProperties> getShapesProperties() {
		ArrayList<ShapeProperties> prop = new ArrayList<>();
		for (ShapePaint shape : shapes) {
			prop.add(shape.getShapeProperties());
		}
		return prop;
	}

	/**
	 * Removes the shape identified
	 * by this id from the state.
	 * @param id the shape's id
	 */
	public void removeShape(String id) {
		for (int i = 0 ; i < shapes.size() ; i++) {
			if (shapes.get(i).getId().equals(id)) {
				shapes.remove(i);
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public HistoryEvent clone() throws CloneNotSupportedException {
		HistoryEvent clone = new HistoryEvent();
		for (ShapePaint shape : shapes) {
			clone.getShapes().add(shape.clone());
		}
		return clone;
	}
}

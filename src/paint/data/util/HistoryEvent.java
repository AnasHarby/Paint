package paint.data.util;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import paint.geom.ShapePaint;

public class HistoryEvent {
	private ArrayList<ShapePaint> shapes;
	
	public HistoryEvent() {
		shapes = new ArrayList<>();
	}
	
	public void showEvent(Canvas canvas) {
		Pane pane = (Pane) canvas.getParent();
		pane.getChildren().clear();
		pane.getChildren().add(canvas);
		for (ShapePaint shape : shapes) {
			shape.draw(pane);
		}
	}
	
	public Collection<ShapePaint> getShapes() {
		return shapes;
	}
	
	public void removeShape(String id) {
		for (int i = 0 ; i < shapes.size() ; i++) {
			if (shapes.get(i).getId().equals(id)) {
				shapes.remove(i);
				return;
			}
		}
	}
}

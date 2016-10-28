package paint.geom.util;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import paint.geom.Point;

public class ShapeMovement {
	Point originalTranslate;
	Point draggingStartPoint;
	EventHandler<MouseEvent> mouseClickHandler =
	new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			originalTranslate = new Point(source.getTranslateX(),
					source.getTranslateY());
			draggingStartPoint = new Point(event.getSceneX(),
					event.getSceneY());
			source.toFront();
			source.setCursor(Cursor.MOVE);
		}
	};
	EventHandler<MouseEvent> mouseDraggingHandler =
	new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			source.setTranslateX(originalTranslate.getX()
					+ event.getSceneX() - draggingStartPoint.getX());
			source.setTranslateY(originalTranslate.getY()
					+ event.getSceneY() - draggingStartPoint.getY());
		}
	};
	EventHandler<MouseEvent> mouseReleaseHandler =
	new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			source.setCursor(Cursor.DEFAULT);
		}
		
	};
	public void addHandlers(Node node) {
		node.setOnMousePressed(mouseClickHandler);
		node.setOnMouseDragged(mouseDraggingHandler);
		node.setOnMouseReleased(mouseReleaseHandler);
	}
}

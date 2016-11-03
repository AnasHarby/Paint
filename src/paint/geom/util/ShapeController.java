package paint.geom.util;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import paint.data.util.CurrentHistoryEvent;
import paint.data.util.History;
import paint.geom.Point;
import paint.geom.ShapePaint;

public class ShapeController {
	private static final String RESIZER_ID = "Resizer";
	private Point originalTranslate = null;
	private Point draggingStartPoint = null;
	double EPSILON = 5;

	EventHandler<MouseEvent> mousePressedHandler =
			new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (source.getId() == null) {
				return;
			} else if (source.getId().startsWith(RESIZER_ID)) {
				source.setCursor(Cursor.V_RESIZE);
			} else {
				originalTranslate = new Point(source.getTranslateX(),
						source.getTranslateY());
				source.setCursor(Cursor.MOVE);
			}
			draggingStartPoint = new Point(event.getSceneX(),
					event.getSceneY());
		}
	};

	EventHandler<MouseEvent> mouseDraggingHandler =
			new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (source.getId() == null) {
				return;
			} else if (source.getId().startsWith(RESIZER_ID)) {
				ShapePaint parent =
						(ShapePaint) source.getUserData();
				Circle resizer = (Circle) source;
				parent.resize(resizer.centerXProperty().doubleValue(),
						resizer.centerYProperty().doubleValue(), event.getX(),
						event.getY());
				System.out.println(resizer.centerXProperty().doubleValue());
				System.out.println(resizer.centerYProperty().doubleValue());
				System.out.println(event.getSceneX());
				System.out.println(event.getSceneY());
				System.out.println("");
			} else {
				source.setTranslateX(originalTranslate.getX()
						+ event.getSceneX() - draggingStartPoint.getX());
				source.setTranslateY(originalTranslate.getY()
						+ event.getSceneY() - draggingStartPoint.getY());
			}
		}
	};

	EventHandler<MouseEvent> mouseReleaseHandler =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (source.getId() == null) {
				return;
			}
			source.setCursor(Cursor.DEFAULT);
			if ((Math.abs(event.getSceneX()
			- draggingStartPoint.getX()) > EPSILON)
			|| (Math.abs(event.getSceneY()
			- draggingStartPoint.getY()) > EPSILON)) {
				//CurrentHistoryEvent.getInstance().getHead().updateHistory();
				History.getHistory().storeShapeChanges(CurrentHistoryEvent.getInstance().getHead());
			}
		}
	};

	EventHandler<MouseEvent> mouseHoverHandler =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (source.getId() == null) {
				return;
			} else if (source.getId().startsWith("Resizer")) {
				source.setCursor(Cursor.V_RESIZE);
			} else {
				source.setCursor(Cursor.MOVE);
			}
		}
	};

	public void addHandlers(Node node) {
		node.setOnMousePressed(mousePressedHandler);
		node.setOnMouseDragged(mouseDraggingHandler);
		node.setOnMouseReleased(mouseReleaseHandler);
		node.setOnMouseMoved(mouseHoverHandler);
	}
}


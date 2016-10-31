package paint.geom.util;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import paint.geom.Point;
import paint.geom.ShapePaint;

public class ShapeController {
	private static final String RESIZER_ID = "Resizer";
	private Point originalTranslate = null;
	private Point draggingStartPoint = null;
	EventHandler<MouseEvent> mouseClickHandler =
			new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (source.getId() == null) {
				originalTranslate = new Point(source.getTranslateX(),
						source.getTranslateY());
				source.setCursor(Cursor.MOVE);
			} else if (source.getId().startsWith(RESIZER_ID)) {
				source.setCursor(Cursor.V_RESIZE);
			}
			draggingStartPoint = new Point(event.getX(),
				event.getY());
		}
	};

	EventHandler<MouseEvent> mouseDraggingHandler =
			new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (source.getId() == null) {
				source.setTranslateX(originalTranslate.getX()
						+ event.getX() - draggingStartPoint.getX());
				source.setTranslateY(originalTranslate.getY()
						+ event.getY() - draggingStartPoint.getY());
			} else if (source.getId().startsWith(RESIZER_ID)) {
				ShapePaint parent =
						(ShapePaint) source.getUserData();
				Rectangle resizer = (Rectangle) source;
				parent.resize(resizer.xProperty().doubleValue(),
						resizer.yProperty().doubleValue(), event.getX(),
						event.getY());
				System.out.println(resizer.xProperty().doubleValue());
				System.out.println(resizer.yProperty().doubleValue());
				System.out.println(event.getX());
				System.out.println(event.getY());
				System.out.println("");
			}
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

	EventHandler<MouseEvent> mouseHoverHandler =
			new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					Node source = (Node) event.getSource();
					if (source.getId() == null) {
						source.setCursor(Cursor.MOVE);
					} else if (source.getId().startsWith("Resizer")) {
						source.setCursor(Cursor.V_RESIZE);
					}
				}
			};

	public void addHandlers(Node node) {
		node.setOnMousePressed(mouseClickHandler);
		node.setOnMouseDragged(mouseDraggingHandler);
		node.setOnMouseReleased(mouseReleaseHandler);
		node.setOnMouseMoved(mouseHoverHandler);
	}
}

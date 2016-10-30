package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import paint.geom.EllipsePaint;
import paint.geom.LinePaint;
import paint.geom.Point;

public class FXMLController implements Initializable {
	@FXML private Canvas canvas;
	@FXML private ColorPicker picker;
	private boolean start = false;
	private Rectangle rect1;
	private Rectangle rect2;
//	private CirclePaint circle;
	private LinePaint line;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		canvas.autosize();
		canvas.getGraphicsContext2D();

	}

	@FXML
	public void act(MouseEvent event) {
		Pane pane = (Pane) canvas.getParent();
		if(!start) {
//			rect1 = new Rectangle(200, 200, 200, 200);
//			rect2 = new Rectangle(200, 200, 100, 100);
//			ShapeController cont = new ShapeController();
//			cont.addHandlers(rect1);
//			rect1.xProperty().bind(rect2.xProperty());
//			rect1.yProperty().bind(rect2.yProperty());
//			rect2.translateXProperty().bind(rect1.translateXProperty());
			//rect2.set
//			rect2.translateYProperty().bind(rect1.translateYProperty());
//			rect2.onMouseDraggedProperty().bind(rect1.onMouseDraggedProperty());
//			rect2.onMouseReleasedProperty().bind(rect1.onMouseReleasedProperty());
//			rect2.onMousePressedProperty().bind(rect1.onMousePressedProperty());
//			rect2.layoutXProperty().bind(rect1.layoutXProperty());
//			rect2.layoutYProperty().bind(rect1.layoutYProperty());
//			rect1.setFill(Color.TRANSPARENT);
//			rect2.setFill(Color.TRANSPARENT);
//			rect1.setStroke(Color.BLACK);
//			rect2.setStroke(Color.BLACK);
//			pane.getChildren().add(rect2);
//			pane.getChildren().add(rect1);
//			rect1.toFront();
			EllipsePaint circle = new EllipsePaint(new Point(400, 400), 200, 100);
			circle.draw(pane);
			circle.showResizers(pane);
//			circle.hideResizers(pane);
			start = true;

		} else {
//			double x1 = 400;
//			double y1 = 500;
//			double x2 = event.getX();
//			double y2 = event.getY();
//			line.resize(x1, y1, x2, y2);
		}
	}
}

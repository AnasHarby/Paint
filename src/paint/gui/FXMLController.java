package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import paint.geom.CirclePaint;
import paint.geom.Point;

public class FXMLController implements Initializable {
	@FXML private Canvas canvas;
	@FXML private ColorPicker picker;
	private boolean start = false;
//	private RectanglePaint rect;
	private CirclePaint circle;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		canvas.autosize();
		canvas.getGraphicsContext2D();

	}

	@FXML
	public void act(MouseEvent event) {
		Pane pane = (Pane) canvas.getParent();
		if(!start) {
//			rect = new RectanglePaint(new Point(200, 200), 200, 200);
//			rect.draw(pane);
			circle = new CirclePaint(new Point(400, 400), 200);
			circle.draw(pane);
			start = true;
		} else {
			double x1 = 200;
			double y1 = 400;
			double x2 = 400;
			double y2 = 800;
			circle.resize(x1, y1, x2, y2);
		}
	}
}

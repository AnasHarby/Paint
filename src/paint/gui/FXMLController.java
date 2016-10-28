package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import paint.geom.Point;
import paint.geom.TrianglePaint;

public class FXMLController implements Initializable {
	@FXML private Canvas canvas;
	@FXML private ColorPicker picker;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		canvas.autosize();
		canvas.getGraphicsContext2D();
	}

	@FXML
	public void act(MouseEvent event) {
		Point point = new Point(event.getX(), event.getY());
		TrianglePaint obj = new TrianglePaint(point, new Point(point.getX() + 100, point.getY())
				, new Point(point.getX(), point.getY() - 100));

		obj.draw((Pane) canvas.getParent());
	}
}

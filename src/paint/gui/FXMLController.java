package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import paint.geom.EllipsePaint;
import paint.geom.Point;

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
		Pane pane = (Pane) canvas.getParent();
		EllipsePaint circle = new EllipsePaint(new Point(400, 400), 200, 100);
		circle.draw(pane);
		circle.showResizers();
	}
}

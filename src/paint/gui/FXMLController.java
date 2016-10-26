package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import paint.geom.EllipsePaint;
import paint.geom.Point;

public class FXMLController implements Initializable {
	@FXML private Canvas canvas;
	private GraphicsContext gc;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		canvas.autosize();
		gc = canvas.getGraphicsContext2D();
	}

	@FXML
	public void act(MouseEvent event) {
		Point point = new Point(event.getX(), event.getY());
		EllipsePaint ellipse = new EllipsePaint(point, 100, 100);
		ellipse.draw((Pane) canvas.getParent());
	}

}

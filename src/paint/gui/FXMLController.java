package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import paint.geom.Point;
import paint.geom.SquarePaint;

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
//		EllipsePaint ellipse = new EllipsePaint(new Point(600, 600), 200, 100);
//		ellipse.fill(Color.BEIGE);
//		ellipse.draw(pane);
//		ellipse.showResizers();

//		ShapePaint rect = new RectanglePaint(new Point(200, 200), 400, 200);
//		rect.fill(Color.BEIGE);
//		rect.draw(pane);
//		rect.showResizers();

//		CirclePaint circle = new CirclePaint(new Point(800, 800), 100);
//		circle.fill(Color.BEIGE);
//		circle.draw(pane);
//		circle.showResizers();

		SquarePaint square = new SquarePaint(new Point(400, 400), 400);
		square.fill(Color.BEIGE);
		square.draw(pane);
		square.showResizers();

//		LinePaint line = new LinePaint(new Point(200, 200), new Point(400, 400));
//		line.draw(pane);
//		line.showResizers();
	}
}

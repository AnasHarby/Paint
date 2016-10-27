package paint.gui.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FXMLControllerTest implements Initializable {

	@FXML private Canvas canvas;

	boolean drawing = true;
	boolean started = false;

	@FXML private Button test1;

	double initX = 0, initY = 0;
	double prevX = 0, prevY = 0;
	private Rectangle rect;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("Initialized");
	}

	@FXML
	public void startDraw(ActionEvent event) {
		drawing = true;
	}

	@FXML
	public void act(MouseEvent event) {
		if (started){
			//drawing = false;
			started = false;
			rect.setWidth(event.getX() - initX);
			rect.setHeight(event.getY() - initY);
		}
		else if (drawing) {
			initX = event.getX();
			initY = event.getY();
			prevX = initX;
			prevY = initY;
			started = true;
			rect = new Rectangle(initX, initY, 0, 0);
			rect.setFill(Color.TRANSPARENT);
			rect.setStroke(Color.BLACK);
			rect.setStrokeWidth(5);
			Pane pane = (Pane) canvas.getParent();
			pane.getChildren().add(rect);
		}
	}

	@FXML
	public void drag(MouseEvent event) {

	}

	@FXML
	public void release(MouseEvent event) {
	}

	@FXML
	public void move(MouseEvent event) {
		if (started) {
			rect.setWidth(prevX - initX);
			rect.setHeight(prevY - initY);
			prevX = event.getX();
			prevY = event.getY();
		}
	}
}


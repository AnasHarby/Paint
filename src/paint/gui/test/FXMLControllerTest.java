package paint.gui.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FXMLControllerTest implements Initializable {

	@FXML private Canvas canvas;
	private GraphicsContext gc;

	boolean drawing = false;
	boolean started = false;

	@FXML private Button test1;

	double initX = 0, initY = 0;
	double prevX = 0, prevY = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialized");
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fill();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);
		gc.stroke();
	}

	@FXML
	public void startDraw(ActionEvent event) {
		drawing = true;
	}

	@FXML
	public void act(MouseEvent event) {
		if (started){
			drawing = false;
			started = false;
			gc.fillRect(initX, initY, event.getX() - initX, event.getY() - initY);
		}
		else if (drawing) {
			initX = event.getX();
			initY = event.getY();
			prevX = initX;
			prevY = initY;
			started = true;
			//gc.moveTo(initX, initY);
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
			gc.clearRect(initX, initY, prevX - initX, prevY - initY);
			gc.fillRect(initX, initY, event.getX() - initX, event.getY() - initY);
			prevX = event.getX();
			prevY = event.getY();
		}
	}
}


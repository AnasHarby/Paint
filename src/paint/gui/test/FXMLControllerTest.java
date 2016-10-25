package paint.gui.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class FXMLControllerTest implements Initializable {

	@FXML private Canvas canvas;
	private GraphicsContext gc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialized");
		gc = canvas.getGraphicsContext2D();
	}

	@FXML
	public void act(MouseEvent event) {
		gc.fillOval(20, 20, 20, 20);
	}
}

package paint.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {
	@FXML private Canvas canvas;
	private GraphicsContext gc;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gc = canvas.getGraphicsContext2D();
	}

	@FXML
	public void act(MouseEvent event) {
		System.out.println("CLICK!");
		gc.fillOval(0, 0, 150, 50);
	}

}

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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import paint.geom.CirclePaint;
import paint.geom.Point;

public class FXMLControllerTest implements Initializable {

	@FXML private Canvas canvas;
	private GraphicsContext gc;

	private boolean drawing = false;
	private boolean started = false;

	@FXML private Button test1;

	private Point center = new Point();

	private double offset = 2;

	private double getRadius(double x1, double y1, double x2, double y2) {
		double dX = Math.abs(x1 - x2);
		double dY = Math.abs(y1 - y2);
		double radius = Math.sqrt(dX * dX + dY * dY);
		return radius;
	}

	private Point getUpperLeft(double x, double y, double w, double h) {
		double upperLeftX = x - w / 2;
		double upperLeftY = y - h / 2;
		return new Point(upperLeftX, upperLeftY);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialized");
		gc = canvas.getGraphicsContext2D();
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
			double radius = getRadius(center.getX(), center.getY(), event.getX(), event.getY());
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			CirclePaint circle = new CirclePaint(center, radius);
			Pane pane = (Pane) canvas.getParent();
			circle.draw(pane);
		}
		else if (drawing) {
			center.setX(event.getX());
			center.setY(event.getY());
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
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			double radius = getRadius(center.getX(), center.getY(), event.getX(), event.getY());
			Point upperleft = getUpperLeft(center.getX(), center.getY(), radius * 2 + offset, radius * 2 + offset);
			gc.setFill(Color.BLACK);
			gc.fillOval(upperleft.getX(), upperleft.getY(), radius * 2 + offset, radius * 2 + offset);
			upperleft = getUpperLeft(center.getX(), center.getY(), radius * 2, radius * 2);
			gc.setFill(Color.WHITESMOKE);
			gc.fillOval(upperleft.getX(), upperleft.getY(), radius * 2, radius * 2);
		}
	}
}


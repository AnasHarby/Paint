package paint.gui.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import paint.geom.CirclePaint;
import paint.geom.Point;
import paint.geom.RectanglePaint;
import paint.geom.ShapePaint;

public class FXMLControllerTest implements Initializable {

	@FXML private Canvas canvas;
	@FXML private Canvas canvas2;
	private GraphicsContext gc;
	private GraphicsContext gc2;
	@FXML private ToggleGroup testingToggleGroup;
	private boolean started = false;
	@FXML private ToggleButton circleButton;
	@FXML private ToggleButton rectangleButton;
	@FXML private ToggleButton pencilDraw;
	@FXML private ToggleButton brushDraw;
	private static final String CIRCLE_BUTTON = "Start Drawing Circles";
	private static final String RECTANGLE_BUTTON = "Start Drawing Rectangles";
	private static final String PENCIL_BUTTON = "Start Pencil Drawing";
	private static final String BRUSH_BUTTON = "Start Brushing";
	private Point init = new Point();
	ShapePaint drawingShape = null;
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
	private Point getRectangleUpperLeft(double x1, double y1, double x2, double y2) {
		return new Point(Math.min(x1, x2), Math.min(y1, y2));
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialized");
		gc = canvas.getGraphicsContext2D();
		gc2 = canvas.getGraphicsContext2D();
	}

	@FXML
	public void startDraw(ActionEvent event) {
		canvas.toFront();
	}
	@FXML
	public void startSketch(ActionEvent event) {
		canvas2.toFront();
	}
	@FXML
	public void act(MouseEvent event) {
		ToggleButton active = (ToggleButton) testingToggleGroup.getSelectedToggle();
		if (active == null) {
			canvas.toBack();
			canvas2.toBack();
			return;
		}
		String name = active.getText();
		switch (name) {
		case CIRCLE_BUTTON:
			//this will be a function call
			if (started){
				started = false;
				double radius = getRadius(init.getX(), init.getY(), event.getX(), event.getY());
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				CirclePaint circle = new CirclePaint(init, radius);
				Pane pane = (Pane) canvas.getParent();
				circle.draw(pane);
				circle.toBack();
			}
			else {
				init.setX(event.getX());
				init.setY(event.getY());
				started = true;
			}
			break;
		case RECTANGLE_BUTTON:
			if (started){
				started = false;
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				ShapePaint rectangle = new RectanglePaint(init, event.getX() - init.getX(), event.getY() - init.getY());
				Pane pane = (Pane) canvas.getParent();
				rectangle.draw(pane);
				rectangle.toBack();
			}
			else {
				init.setX(event.getX());
				init.setY(event.getY());
				started = true;
			}
			break;
		case PENCIL_BUTTON:
			gc2.moveTo(event.getX(), event.getY());
			gc2.stroke();
			break;
		case BRUSH_BUTTON:
			gc2.moveTo(event.getX(), event.getY());
			break;
		default:
			break;
		}
		
	}

	@FXML
	public void drag(MouseEvent event) {
		ToggleButton active = (ToggleButton) testingToggleGroup.getSelectedToggle();
		if (active == null) {
			return;
		}
		String name = active.getText();
		switch (name) {
		case PENCIL_BUTTON:
			gc2.lineTo(event.getX(), event.getY());
			gc2.stroke();
			break;
		case BRUSH_BUTTON:
			break;
		default:
			break;
		}
	}

	@FXML
	public void release(MouseEvent event) {
	}

	@FXML
	public void move(MouseEvent event) {
		ToggleButton active = (ToggleButton) testingToggleGroup.getSelectedToggle();
		if (active == null) {
			return;
		}
		String name = active.getText();
		switch (name) {
		case CIRCLE_BUTTON:
			if (started) {
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				double radius = getRadius(init.getX(), init.getY(), event.getX(), event.getY());
				Point upperleft = getUpperLeft(init.getX(), init.getY(), radius * 2 + offset, radius * 2 + offset);
				upperleft = getUpperLeft(init.getX(), init.getY(), radius * 2, radius * 2);
				gc.strokeOval(upperleft.getX(), upperleft.getY(), radius * 2, radius * 2);
			}
			break;
		case RECTANGLE_BUTTON:
			if (started) {
				Point upperLeft = getRectangleUpperLeft(init.getX(), init.getY(), event.getX(), event.getY());
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.strokeRect(upperLeft.getX(), upperLeft.getY(),
						Math.abs(init.getX() - event.getX()),
						Math.abs(init.getY() - event.getY()));
			}
			break;
		default:
			break;
		}
	}
}


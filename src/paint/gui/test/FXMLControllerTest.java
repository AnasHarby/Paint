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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import paint.geom.CirclePaint;
import paint.geom.Point;
import paint.geom.RectanglePaint;
import paint.geom.ShapePaint;

public class FXMLControllerTest implements Initializable {

	@FXML private Canvas canvas;
	@FXML private Canvas freeDrawingCanvas;
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
	Shape drawingShape = null;

	private double getRadius(double x1, double y1, double x2, double y2) {
		double dX = Math.abs(x1 - x2);
		double dY = Math.abs(y1 - y2);
		double radius = Math.sqrt(dX * dX + dY * dY);
		return radius;
	}

	private Point getRectangleUpperLeft(double x1, double y1, double x2, double y2) {
		return new Point(Math.min(x1, x2), Math.min(y1, y2));
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialized");
		gc = canvas.getGraphicsContext2D();
		gc2 = freeDrawingCanvas.getGraphicsContext2D();
	}

	@FXML
	public void startDraw(ActionEvent event) {
		canvas.toFront();
	}
	@FXML
	public void startSketch(ActionEvent event) {
		freeDrawingCanvas.toFront();
	}
	@FXML
	public void act(MouseEvent event) {
		ToggleButton active = (ToggleButton) testingToggleGroup.getSelectedToggle();
		if (active == null) {
			canvas.toBack();
			freeDrawingCanvas.toBack();
			return;
		}
		String name = active.getText();
		Pane pane = (Pane) canvas.getParent();
		switch (name) {
		case CIRCLE_BUTTON:
			//this will be a function call
			if (started){
				started = false;
				double radius = getRadius(init.getX(), init.getY(), event.getX(), event.getY());
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				CirclePaint circle = new CirclePaint(init, radius);
				pane.getChildren().remove(drawingShape);
				drawingShape = null;
				circle.draw(pane);
				circle.toBack();
			}
			else {
				init.setX(event.getX());
				init.setY(event.getY());
				drawingShape = new Circle(event.getX(), event.getY(), 0);
				pane.getChildren().add(drawingShape);
				drawingShape.setFill(Color.TRANSPARENT);
				drawingShape.setStroke(Color.BLACK);
				drawingShape.toBack();
				started = true;
			}
			break;
		case RECTANGLE_BUTTON:
			if (started){
				started = false;
				ShapePaint rectangle
				= new RectanglePaint(init,
					event.getX() - init.getX(),
					event.getY() - init.getY());
				pane.getChildren().remove(drawingShape);
				rectangle.draw(pane);
				drawingShape = null;
				rectangle.toBack();
			}
			else {
				init.setX(event.getX());
				init.setY(event.getY());
				drawingShape = new Rectangle(event.getX(),
						event.getY(), 0, 0);
				drawingShape.setFill(Color.TRANSPARENT);
				drawingShape.setStroke(Color.BLACK);
				pane.getChildren().add(drawingShape);
				drawingShape.toBack();
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
			canvas.toBack();
			freeDrawingCanvas.toBack();
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
				double radius = getRadius(init.getX(), init.getY(), event.getX(), event.getY());
				Circle circle = (Circle) drawingShape;
				circle.setRadius(radius);
			}
			break;
		case RECTANGLE_BUTTON:
			if (started) {
				Rectangle rect = (Rectangle) drawingShape;
				Point upperLeft = getRectangleUpperLeft(init.getX(),
					init.getY(), event.getX(), event.getY());
				rect.setX(upperLeft.getX());
				rect.setY(upperLeft.getY());
				rect.setWidth(Math.abs(event.getX() - init.getX()));
				rect.setHeight(Math.abs(event.getY() - init.getY()));
				
			}
			break;
		default:
			break;
		}
	}
}


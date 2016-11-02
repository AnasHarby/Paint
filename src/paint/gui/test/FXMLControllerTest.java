package paint.gui.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import paint.data.util.History;
import paint.data.util.HistoryEvent;
import paint.data.util.JsonDataHandler;
import paint.geom.CirclePaint;
import paint.geom.Point;
import paint.geom.ShapePaint;
import paint.geom.util.ShapeController;
import paint.geom.util.ShapeFactory;

public class FXMLControllerTest implements Initializable {
	private static final String CIRCLE_BUTTON = "circle";
	private static final String RECTANGLE_BUTTON = "rectangle";
	private static final String PENCIL_BUTTON = "pencilDraw";
	private static final String BRUSH_BUTTON = "brushDraw";

	@FXML private Canvas canvas;
	@FXML private Canvas freeDrawingCanvas;
	@FXML private ToggleGroup testingToggleGroup;
	@FXML private ToggleButton circleButton;
	@FXML private ToggleButton rectangleButton;
	@FXML private ToggleButton pencilDraw;
	@FXML private ToggleButton brushDraw;

	private GraphicsContext gc;
	private GraphicsContext gc2;
	private boolean started = false;
	private Point init = new Point();
	private Shape drawingShape = null;
	History history;
	HistoryEvent current = new HistoryEvent();
	JsonDataHandler jsonData = new JsonDataHandler();


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
		Pane pane = (Pane) canvas.getParent();
		Shape circle1 = new Circle(200, 200, 200, Color.TRANSPARENT);
		circle1.setStroke(Color.BLACK);
		Shape circle2 = new Circle(400, 200, 5);
		ShapeController sc = new ShapeController();
		sc.addHandlers(circle2);
		circle1.layoutXProperty();
		history = new History();
		history.storeShapeChanges(current);
		pane.getParent().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.Y) {
					HistoryEvent temp = history.redo(canvas);
					if (temp != null) {
						current = temp;
					}
				} else if (event.getCode() == KeyCode.Z) {
					HistoryEvent temp = history.undo(canvas);
					if (temp != null) {
						current = temp;
					}
				} else if (event.getCode() == KeyCode.S) {
					jsonData.saveJson(current);
				} else if (event.getCode() == KeyCode.L) {
					history = new History();
					current = jsonData.loadJson(canvas);
					history.storeShapeChanges(current);
				}
			}

		});
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
		String name = active.getId();
		Pane pane = (Pane) canvas.getParent();
		switch (name) {
		case CIRCLE_BUTTON:
			if (started){
				started = false;
				double radius = getRadius(init.getX(), init.getY(), event.getX(), event.getY());
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				CirclePaint circle = new CirclePaint(init, radius);
				pane.getChildren().remove(drawingShape);
				drawingShape = null;
				circle.draw(pane);
				circle.toBack();
				circle.showResizers();
			} else {
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
				= ShapeFactory.getInstance().createShape(RECTANGLE_BUTTON, init.getX(), init.getY(), event.getX(), event.getY());//new RectanglePaint(init.getX(), init.getY(), event.getX(), event.getY());
				pane.getChildren().remove(drawingShape);
				ArrayList<ShapePaint> shapes = new ArrayList<>();
				shapes.add(rectangle);
				current.getShapes().add(rectangle);
				history.storeShapeChanges(current);
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
		String name = active.getId();
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
		String name = active.getId();
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


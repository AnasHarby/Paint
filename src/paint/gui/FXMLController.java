package paint.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import paint.data.util.CurrentHistoryEvent;
import paint.geom.Point;
import paint.geom.ShapePaint;
import paint.geom.util.ShapeFactory;

public class FXMLController implements Initializable {
	private enum State {
		EDITING, ERASING, FREE_DRAWING,
		SHAPING, BIASING, TRIANGLE_BIASING, TRIANGLE_SHAPING,
		TRIANGLE_DRAWING, REMOVING
	}
	private static final String PENCIL_BUTTON = "pencilButton";
	private static final String ERASER_BUTTON = "eraserButton";
	private static final String MOVE_BUTTON = "moveButton";
	private static final String FILL_BUTTON = "fillButton";
	private static final String PICK_BUTTON = "pickButton";
	private static final String REMOVE_BUTTON = "remover";
	private static final String TRIANGLE_BUTTON = "triangle";
	private static final String LINE_KEY = "line";
	private static final String TRIANGLE_KEY = "triangle";
	@FXML private Canvas canvas;
	@FXML private Button newButton;
	@FXML private ColorPicker picker;
	@FXML private ButtonBar buttonBar;
	@FXML private ToggleGroup toggleGroup;
	@FXML private ToggleButton pencilButton;
	@FXML private ToggleButton eraserButton;
	@FXML private ToggleButton moveButton;
	@FXML private ToggleButton fillButton;
	@FXML private ToggleButton pickButton;
	@FXML private ToggleButton removeButton;
	@FXML private ToggleButton lineButton;
	@FXML private ToggleButton rectangleButton;
	@FXML private ToggleButton squareButton;
	@FXML private ToggleButton triangleButton;
	@FXML private ToggleButton ellipseButton;
	@FXML private ToggleButton circleButton;
	private State state;
	//	private Color fillColor;
	//	private Color borderColor;
	private ShapePaint currentShape;
	private String currShapeID;
	private Point biasingPoint;
	private Point triangleSecondPoint;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		state = State.EDITING;
	}

	@FXML
	public void act(ActionEvent event) {
		ToggleButton active = (ToggleButton)
				toggleGroup.getSelectedToggle();
		if (active == null) {
			state = State.EDITING;
			canvas.toBack();
			currentShape = null;
			return;
		}
		canvas.toFront();
		String id = active.getId();
		switch (id) {
		case PENCIL_BUTTON:
			break;
		case ERASER_BUTTON:
			break;
		case MOVE_BUTTON:
			break;
		case FILL_BUTTON:
			break;
		case PICK_BUTTON:
			break;
		case REMOVE_BUTTON:
			state = State.REMOVING;
			canvas.toBack();
			break;
		case TRIANGLE_BUTTON:
			state = State.TRIANGLE_BIASING;
			currShapeID = LINE_KEY;
			break;
		default:
			state = State.BIASING;
			currShapeID = id;
			break;
		}
	}

	private void drawShape(MouseEvent event) {
		Pane pane = (Pane) canvas.getParent();
		switch (state) {
		case BIASING:
		case TRIANGLE_BIASING:
			currentShape = ShapeFactory.getInstance().
			createShape(currShapeID, event.getX(),
					event.getY(), event.getX(), event.getY());
			currentShape.draw(pane);
			biasingPoint = new Point(event.getX(),
					event.getY());
			canvas.toFront();
			break;
		case SHAPING:
		case TRIANGLE_SHAPING:
			currentShape.remove(pane);
			currentShape = ShapeFactory.getInstance()
					.createShape(currShapeID, biasingPoint.getX(),
							biasingPoint.getY(), event.getX(),
							event.getY());
			currentShape.draw(pane);
			currentShape.setOnMouseClicked(
					removeHandler);
			canvas.toFront();
			break;
		case TRIANGLE_DRAWING:
			currentShape.remove(pane);
			currentShape = ShapeFactory.getInstance()
					.createShape(TRIANGLE_KEY, biasingPoint.getX(),
							biasingPoint.getY(), triangleSecondPoint.getX(),
							triangleSecondPoint.getY(), event.getX(),
							event.getY());
			currentShape.draw(pane);
			currentShape.setOnMouseClicked(
					removeHandler);
			canvas.toFront();
			break;
		default:
			break;
		}
	}

	@FXML
	public void actCanvas(MouseEvent event) {
		switch (state) {
		case BIASING:
			drawShape(event);
			state = State.SHAPING;
			break;
		case SHAPING:
			drawShape(event);
			state = State.BIASING;
			currentShape.showResizers();
			CurrentHistoryEvent.getInstance().getHead().getShapes().add(currentShape);
			CurrentHistoryEvent.getInstance().getHead().updateHistory();
			break;
		case TRIANGLE_BIASING:
			drawShape(event);
			state = State.TRIANGLE_SHAPING;
			break;
		case TRIANGLE_SHAPING:
			drawShape(event);
			triangleSecondPoint = new Point(
					event.getX(), event.getY());
			state = State.TRIANGLE_DRAWING;
			break;
		case TRIANGLE_DRAWING:
			drawShape(event);
			state = State.TRIANGLE_BIASING;
			currentShape.showResizers();
			CurrentHistoryEvent.getInstance().getHead().getShapes().add(currentShape);
			CurrentHistoryEvent.getInstance().getHead().updateHistory();
			break;
		default:
			break;
		}
	}

	@FXML
	public void moveOnCanvas(MouseEvent event) {
		switch (state) {
		case SHAPING:
		case TRIANGLE_SHAPING:
		case TRIANGLE_DRAWING:
			drawShape(event);
			break;
		default:
			break;
		}
	}

	@FXML
	public void reset(ActionEvent event) {
		Pane pane = (Pane) canvas.getParent();
		while (!pane.getChildren().isEmpty()) {
			pane.getChildren().remove(0);
		}
		pane.getChildren().add(canvas);
		ToggleButton active = (ToggleButton)
				toggleGroup.getSelectedToggle();
		if (active != null)
			active.selectedProperty().set(false);
		state = State.EDITING;
	}

	@FXML
	public void importClass(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
				new ExtensionFilter(".Class files (*.class)", "*.class"));
		chooser.setTitle("Import");
		File file = chooser.showOpenDialog(
				canvas.getScene().getWindow());
		/*The .class file, this can be taken to the
		 * MainGUI... Should be easy to iterate after this
		 * over the nodes in "buttonBar" and activate the button
		 * with the key in the .class file*/
	}
	private EventHandler<MouseEvent> removeHandler =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if (state ==
					State.REMOVING) {
				Node source = (Node)
						event.getSource();
				CurrentHistoryEvent.getInstance().
				getHead().removeShape(source.getId());
				CurrentHistoryEvent.getInstance().
				getHead().updateHistory();
				CurrentHistoryEvent.getInstance().
				getHead().showEvent(canvas);
				canvas.toBack();
			}
		}
	};
}

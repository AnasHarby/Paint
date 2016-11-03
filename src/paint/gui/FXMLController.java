package paint.gui;

import java.awt.MenuItem;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import paint.data.util.CurrentHistoryEvent;
import paint.data.util.DataHandler;
import paint.data.util.History;
import paint.data.util.HistoryEvent;
import paint.geom.Point;
import paint.geom.ShapePaint;
import paint.geom.util.ShapeFactory;
import paint.plugins.PluginLoader;

public class FXMLController implements Initializable {
	private enum State {
		EDITING, SHAPING, BIASING, TRIANGLE_BIASING, TRIANGLE_SHAPING,
		TRIANGLE_DRAWING, REMOVING, FILLING, COLOR_PICKING, ROTATING
	}
	private static final ObservableList<String> BORDERS =
			FXCollections.observableArrayList( "1px",
					"2px", "4px", "6px",
					"8px", "10px", "12px");
	private static final String PENCIL_BUTTON = "pencilButton";
	private static final String ERASER_BUTTON = "eraserButton";
	private static final String ROTATE_BUTTON = "rotator";
	private static final String FILL_BUTTON = "bucket";
	private static final String PICK_BUTTON = "dropper";
	private static final String REMOVE_BUTTON = "remover";
	private static final String TRIANGLE_BUTTON = "triangle";
	private static final String LINE_KEY = "line";
	private static final String TRIANGLE_KEY = "triangle";
	private static final String ROTATION_REGEX = "^([+-]?\\d*\\.?\\d*)$";
	@FXML private Canvas canvas;
	@FXML private Button newButton;
	@FXML private ColorPicker picker;
	@FXML private ButtonBar buttonBar;
	@FXML private ToggleGroup toggleGroup;
	@FXML private ChoiceBox<String> borderWidthPicker;
	private State state;
	private double borderWidth;
	private ShapePaint currentShape;
	private String currShapeID;
	private Point biasingPoint;
	private Point triangleSecondPoint;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		state = State.EDITING;
		picker.setValue(Color.TRANSPARENT);
		borderWidth = 1;
		buildBorderChooser();
		CurrentHistoryEvent.getInstance().
		getHead().updateHistory();
		for (String key : ShapeFactory.getInstance()
				.getRegisteredShapes().keySet()) {
			for (Node shape : buttonBar.getButtons()) {
				if (shape.getId().equals(key)) {
					shape.setDisable(false);
					break;
				}
			}
		}
	}

	private void buildBorderChooser() {
		borderWidthPicker.setItems(BORDERS);
		borderWidthPicker.setValue(BORDERS.get(0));
		borderWidthPicker.getSelectionModel()
		.selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
						ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {
							borderWidth = Integer.
							parseInt(BORDERS.get(
							newValue.intValue()).split("px")[0]);
						}
		});
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
		case ROTATE_BUTTON:
			state = State.ROTATING;
			canvas.toBack();
			break;
		case FILL_BUTTON:
			state = State.FILLING;
			canvas.toBack();
			break;
		case PICK_BUTTON:
			state = State.COLOR_PICKING;
			canvas.toBack();
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
			currentShape.setFill(picker.getValue());
			currentShape.setBorderWidth(borderWidth);
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
			currentShape.setFill(picker.getValue());
			currentShape.setBorderWidth(borderWidth);
			currentShape.draw(pane);
			currentShape.setOnMouseClicked(
					toolsHandler);
			canvas.toFront();
			break;
		case TRIANGLE_DRAWING:
			currentShape.remove(pane);
			currentShape = ShapeFactory.getInstance()
					.createShape(TRIANGLE_KEY, biasingPoint.getX(),
							biasingPoint.getY(), triangleSecondPoint.getX(),
							triangleSecondPoint.getY(), event.getX(),
							event.getY());
			currentShape.setFill(picker.getValue());
			currentShape.setBorderWidth(borderWidth);
			currentShape.draw(pane);
			currentShape.setOnMouseClicked(
					toolsHandler);
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
		case EDITING:
		case FILLING:
		case COLOR_PICKING:
		case ROTATING:
		case REMOVING:
			canvas.toBack();
			break;
		default:
			break;
		}
	}

	@FXML
	public void reset(ActionEvent event) {
		Pane pane = (Pane) canvas.getParent();
		pane.getChildren().clear();
		pane.getChildren().add(canvas);
		ToggleButton active = (ToggleButton)
				toggleGroup.getSelectedToggle();
		if (active != null) {
			active.selectedProperty().set(false);
		}
		History.clearHistory();
		CurrentHistoryEvent.getInstance().
		setHead(new HistoryEvent());
		CurrentHistoryEvent.getInstance().
		getHead().updateHistory();
		state = State.EDITING;
	}

	@FXML
	public void importClass(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
				new ExtensionFilter("Class files (*.class, *.jar)", "*.class", "*.jar"));
		chooser.setTitle("Import");
		File file = chooser.showOpenDialog(
				canvas.getScene().getWindow());
		String key = PluginLoader.loadClass(file);
		for (Node shape : buttonBar.getButtons()) {
			if (shape.getId().equals(key)) {
				shape.setDisable(false);
				break;
			}
		}
	}
	private EventHandler<MouseEvent> toolsHandler =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			Node source = (Node) event.getSource();
			if (state ==
					State.REMOVING) {
				CurrentHistoryEvent.getInstance().
				getHead().removeShape(source.getId());
				CurrentHistoryEvent.getInstance().
				getHead().updateHistory();
				CurrentHistoryEvent.getInstance().
				getHead().showEvent(canvas);
				canvas.toBack();
			} else if (state ==
					State.FILLING) {
				CurrentHistoryEvent.getInstance()
				.getHead().getShape(source.getId())
				.setFill(picker.getValue());
				CurrentHistoryEvent.getInstance().
				getHead().updateHistory();
			} else if (state ==
					State.COLOR_PICKING) {
				picker.setValue(CurrentHistoryEvent
						.getInstance().getHead()
						.getShape(source.getId())
						.getFill());
				CurrentHistoryEvent.getInstance().
				getHead().updateHistory();
			} else if (state ==
					State.ROTATING) {
				double angle = getRotationValue();
				if (!Double.isNaN(angle)) {
					CurrentHistoryEvent.getInstance()
					.getHead().getShape(source.getId())
					.rotate(angle);
					CurrentHistoryEvent.getInstance().
					getHead().updateHistory();
				}
			}
		}
	};
	@FXML
	public void save(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
				new ExtensionFilter("JSON Files (*.json)", "*.json"));
		chooser.getExtensionFilters().add(
				new ExtensionFilter("XML Files (*.xml)", "*.xml"));
		chooser.setTitle("Save");
		File file = chooser.showSaveDialog(
				canvas.getScene().getWindow());
		if (file != null) {
			DataHandler.getInstance().
			saveData(file.getAbsolutePath(),
			CurrentHistoryEvent.getInstance().getHead());
		}
	}

	@FXML
	public void load(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
				new ExtensionFilter("Save Files (*.json, *.xml)",
						"*.json", "*.xml"));
		chooser.setTitle("Load");
		File file = chooser.showOpenDialog(
				canvas.getScene().getWindow());
		if (file != null) {
			History.clearHistory();
			try {
				CurrentHistoryEvent.getInstance().
				setHead(DataHandler.getInstance().
				loadData(file.getAbsolutePath()).clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			//Sorry, couldn't think of a better way.
			for (ShapePaint shape :
				CurrentHistoryEvent.getInstance().getHead().getShapes()) {
				shape.setOnMouseClicked(toolsHandler);
			}
			CurrentHistoryEvent.getInstance().getHead().updateHistory();
			CurrentHistoryEvent.getInstance().getHead().showEvent(canvas);
		}
	}

	@FXML
	public void undo(ActionEvent event) {
		HistoryEvent temp = History.getHistory().undo(canvas);
		if (temp != null) {
			CurrentHistoryEvent.getInstance().setHead(temp);
		}
		CurrentHistoryEvent.getInstance().getHead().showEvent(canvas);
	}

	@FXML
	public void redo(ActionEvent event) {
		HistoryEvent temp = History.getHistory().redo(canvas);
		if (temp != null) {
			CurrentHistoryEvent.getInstance().setHead(temp);
		}
		CurrentHistoryEvent.getInstance().getHead().showEvent(canvas);
	}

	@FXML
	public void close(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	public void changeWidth(ActionEvent event) {
		MenuItem width = (MenuItem)
				event.getSource();
		System.out.print(width.getName());
	}

	private double getRotationValue() {
		TextInputDialog dialog = new TextInputDialog("0");
		dialog.setHeaderText("Set Rotation Angle");
		dialog.setTitle("Set Rotation Angle");
		dialog.setGraphic(null);
		dialog.getEditor().textProperty().addListener(
				new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue
					<? extends String> observable, String oldValue,
					String newValue) {
				if (newValue.matches(ROTATION_REGEX)) {
					dialog.getEditor().setText(newValue);
				} else {
					dialog.getEditor().setText(oldValue);
				}
			}
		});
		dialog.setContentText("Please enter the required angle");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return Double.parseDouble(result.get());
		}
		return Double.NaN;
	}

}

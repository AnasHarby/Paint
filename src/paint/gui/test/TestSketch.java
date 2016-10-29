package paint.gui.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestSketch extends Application {
	boolean drawing = false;
	double initX = 0, initY = 0;
	double prevX = 0, prevY = 0;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new StackPane();
		Button button = new Button("draw");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Button Clicked");
				drawing = true;
			}
		});

		Canvas canvas = new Canvas(800, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUE);
		gc.setFill(Color.BLUE);
		canvas.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            	if (event.getClickCount() > 1) {
//            		gc.setLineCap(StrokeLineCap.BUTT);
//                    gc.setLineJoin(StrokeLineJoin.BEVEL);
//                    gc.setLineWidth(15);
            	}
//                gc.beginPath();
            	if(!drawing) {
                	initX = event.getX();
                	initY = event.getY();
                    gc.moveTo(initX, initY);
                    drawing = true;
            	} else {
            		gc.fillRect(initX, initY, event.getX() - initX, event.getY() - initY);
            		drawing = false;
            	}
//                gc.stroke();
            }
        });

		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(drawing) {
					gc.fillRect(initX, initY, event.getX() - initX, event.getY() - initY);
				}
//				 else {
//					gc.fillOval(event.getX(), event.getY(), 15, 10);
//				}
			}

		});
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gc.closePath();
			}

		});
		canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (drawing) {
					gc.clearRect(initX, initY, prevX - initX, prevY - initY);
					gc.fillRect(initX, initY, event.getX() - initX, event.getY() - initY);
					prevX = event.getX();
					prevY = event.getY();
				}
			}

		});
		root.getChildren().addAll(canvas);
		primaryStage.setTitle("Paint");
        primaryStage.setScene(new Scene(root, 1065, 600));
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package paint.gui.test;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import paint.geom.Point;
import paint.geom.ShapePaint;
import paint.geom.TrianglePaint;

/*
 * Example on using Canvas with
 * MouseEvent and GraphicsContext.
 */
public class Test extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Paint");

        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER);
        stack.setPrefHeight(350);
        stack.setPrefWidth(650);
        Canvas canvas = new Canvas(650, 350);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> gc.fillOval(15, 15, 15, 15));
        stack.getChildren().add(canvas);
        Scene scene = new Scene(stack, 650, 350);
        scene.setFill(Color.WHITE);
        ShapePaint ellipse = new TrianglePaint(new Point(0, 0),
        		new Point(200, 200), new Point(0, 200));
        ellipse.draw(stack);

        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

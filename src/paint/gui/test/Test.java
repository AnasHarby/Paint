package paint.gui.test;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import paint.geom.EllipsePaint;
import paint.geom.Point;
import paint.geom.PolygonPaint;
import paint.geom.RectanglePaint;
import paint.geom.ShapePaint;
import paint.geom.SquarePaint;
import paint.geom.TrianglePaint;

/*
 * Example on using Canvas with
 * MouseEvent and GraphicsContext.
 */
public class Test extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Paint");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 2.5, 2.5, 2.5));
        Canvas canvas = new Canvas(750, 750);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> gc.fillOval(15, 15, 15, 15));
        grid.add(canvas, 4, 4);
        Scene scene = new Scene(grid, 1000, 1000);
        scene.setFill(Color.WHITE);
        ShapePaint ellipse = new TrianglePaint(new Point(0, 0), new Point(600, 600), new Point(0, 600));
        ellipse.draw(grid);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

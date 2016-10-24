package paint.gui.test.fxml;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import paint.geom.Point;
import paint.geom.ShapePaint;
import paint.geom.TrianglePaint;

public class TestFXML extends Application {

	@Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        primaryStage.setTitle("Paint");
        ShapePaint ellipse = new TrianglePaint(new Point(0, 0),
        		new Point(200, 200), new Point(0, 200));
        ellipse.draw(root);
        primaryStage.setScene(new Scene(root, 700, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

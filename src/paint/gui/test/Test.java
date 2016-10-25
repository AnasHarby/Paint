package paint.gui.test;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import paint.geom.EllipsePaint;
import paint.geom.Point;

public class Test extends Application {

	@Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("FXMLTest.fxml"));
        primaryStage.setTitle("Paint");
        primaryStage.setScene(new Scene(root, 1065, 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

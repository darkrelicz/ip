package dawn;

import java.io.IOException;

import dawn.Dawn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dawn using FXML.
 */
public class Main extends Application {

    private Dawn dawnBot = new Dawn("data/data.csv");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDawn(dawnBot);  // inject the Dawn instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

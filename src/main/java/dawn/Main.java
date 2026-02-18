package dawn;

import java.io.IOException;

import dawn.Dawn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dawn using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            /*
            AI USAGE
            AI warned the possiblility of a null object being returned if
            IOException was thrown during the initialization of a Dawn 
            object. 

            Shifted the initialization of Dawn object into the try-catch
            below to prevent using an invalid instance due to IOExceptions
            */ 
            Dawn dawnBot = new Dawn("data/data.csv");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Dawn");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/DaDuke.png")));
            fxmlLoader.<MainWindow>getController().setDawn(dawnBot);  // inject the Dawn instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

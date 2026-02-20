package bobby.gui;

import java.io.IOException;

import bobby.Bobby;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX entry point for Bobby (GUI).
 */
public class Main extends Application {

    private final Bobby bobby = new Bobby("data/bobby.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bobby");
            
            fxmlLoader.<MainWindow>getController().setBobby(bobby);
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

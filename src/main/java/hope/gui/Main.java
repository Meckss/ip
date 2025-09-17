package hope.gui;

import java.io.IOException;

import hope.Hope;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {



    @Override
    public void start(Stage stage) {
        try {
            Hope hope = new Hope();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(hope);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

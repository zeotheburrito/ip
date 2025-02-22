package jeff.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jeff.chatbot.Jeff;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jeff jeff;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            this.jeff = new Jeff("data/jeff.txt", fxmlLoader.getController());
            fxmlLoader.<MainWindow>getController().setJeff(jeff);
            fxmlLoader.<MainWindow>getController().showStartUpMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

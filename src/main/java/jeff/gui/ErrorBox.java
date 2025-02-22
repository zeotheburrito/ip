package jeff.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents an error box consisting of an error message.
 */
public class ErrorBox extends HBox {
    @FXML
    private Label error;

    private ErrorBox(String msg) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ErrorBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        error.setText(error.getText() + " " + msg);
    }

    public static ErrorBox getErrorMessage(String msg) {
        return new ErrorBox(msg);
    }
}

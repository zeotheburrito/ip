package jeff.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jeff.chatbot.Jeff;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jeff jeff;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/DaJeff.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the jeff instance */
    public void setJeff(Jeff j) {
        this.jeff = j;
    }

    /**
     * Adds DialogBox with greeting message to the dialog container on start up.
     */
    public void showStartUpMessage() {
        DialogBox greetingMsg = DialogBox.getJeffDialog(this.jeff.getGreeting(), jeffImage);
        dialogContainer.getChildren().add(greetingMsg);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing jeff's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jeff.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJeffDialog(response, jeffImage)
        );
        userInput.clear();
    }

    /**
     * Closes the application window.
     */
    public void closeWindow() {
        Platform.exit();
    }

    public void showError(String msg) {
        dialogContainer.getChildren().add(ErrorBox.getErrorMessage(msg));
    }
}

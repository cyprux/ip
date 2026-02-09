package bobby.gui;

import bobby.Bobby;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main JavaFX window.
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

    private Bobby bobby;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image bobbyImage =
            new Image(this.getClass().getResourceAsStream("/images/DaBobby.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bobby instance. */
    public void setBobby(Bobby b) {
        bobby = b;

        // Optional: show greeting once at startup
        dialogContainer.getChildren().add(
                DialogBox.getBobbyDialog(bobby.getWelcomeMessage(), bobbyImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobby.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobbyDialog(response, bobbyImage)
        );

        userInput.clear();
    }
}

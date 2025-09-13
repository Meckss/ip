package hope.gui;

import java.io.IOException;

import hope.Hope;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code Gui} class represents the graphical user interface for a chatbot application
 * using JavaFX. It manages the layout, user input, and dialog display for interactions
 * with the {@code Hope} chatbot. The interface includes a scrollable dialog container,
 * a text input field, and a send button, styled to create a conversational experience.
 */
public class Gui extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Hope hope;
    private Image hopeImage = new Image(this.getClass().getResourceAsStream("/images/donquixote.jpeg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dante.jpeg"));

    /**
     * Initializes and starts the JavaFX application, setting up the GUI components,
     * layout, and event handlers. Configures the window with a fixed size, a scrollable
     * dialog area, a user input field, and a send button. The layout is styled to ensure
     * proper positioning and scrolling behavior for the conversation.
     *
     * @param stage The primary stage for this application, onto which the application
     *              scene is set.
     */
    @Override
    public void start(Stage stage) {
        //Setting up required components
        try {
            hope = new Hope();
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            userInput = new TextField();
            sendButton = new Button("Send");

            sendButton.setOnMouseClicked((event) -> {
                handleUserInput();
            });
            userInput.setOnAction((event) -> {
                handleUserInput();
            });

            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

            scene = new Scene(mainLayout);

            stage.setScene(scene);
            stage.show();

            //Formatting the window to look as expected

            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            mainLayout.setPrefSize(400.0, 600.0);

            scrollPane.setPrefSize(385, 535);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            scrollPane.setVvalue(1.0);
            scrollPane.setFitToWidth(true);

            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);

            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);

            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);

            AnchorPane.setLeftAnchor(userInput, 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);

            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

            //More code to be added here later
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Processes user input by creating dialog boxes for both the user's input and
     * the chatbot's response, appending them to the dialog container. Clears the
     * input field after processing. If the chatbot's response indicates termination,
     * disables the input field and send button.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String hopeText = hope.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getHopeDialog(hopeText, hopeImage)
        );
        userInput.clear();
        if (hopeText.equals("Fare thee well, noble friend!")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}

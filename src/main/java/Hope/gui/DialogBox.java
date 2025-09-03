package Hope.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code DialogBox} class represents a customizable dialog box component for a JavaFX
 * chatbot application. It displays a text message alongside an image, typically used to
 * represent a user or chatbot message in a conversational interface. The dialog box can
 * be flipped to adjust the alignment of the text and image for different speakers.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a new {@code DialogBox} with the specified text and image.
     * The text is displayed in a label with word wrapping enabled, and the image
     * is shown in an {@code ImageView} with fixed dimensions. The default alignment
     * places the image on the right and the text on the left.
     *
     * @param s The text to display in the dialog box.
     * @param i The image to display alongside the text.
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the alignment and order of the dialog box's components, moving the image
     * to the left and the text to the right. This is typically used to differentiate
     * between user and chatbot messages in the conversation layout.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for the user's message with the specified text and image.
     * The dialog box retains the default alignment (image on the right, text on the left).
     *
     * @param s The text of the user's message.
     * @param i The image representing the user.
     * @return A new {@code DialogBox} instance configured for the user's message.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a dialog box for the chatbot's (Hope's) message with the specified text
     * and image. The dialog box is flipped to have the image on the left and the text
     * on the right.
     *
     * @param s The text of the chatbot's message.
     * @param i The image representing the chatbot.
     * @return A new {@code DialogBox} instance configured for the chatbot's message.
     */
    public static DialogBox getHopeDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}


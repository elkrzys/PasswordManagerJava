package pl.lipiec.ViewExtensions;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Simple message box class used to display information, warning or error to user.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class MessageBox {
    /**
     * Alert object initialized later with specified method.
     */
    private static Alert alert;
    
    /**
     * Shows warning dialog to user with specified title and message.
     * @param title String title of the message
     * @param message String content of the message
     */
    public static void showWarning(String title, String message){
       alert = new Alert(Alert.AlertType.WARNING);
       setMessage(title, message);
    }
    
    /**
     * Shows information dialog to user with specified title and message.
     * @param title String title of the message
     * @param message String content of the message
     */
    public static void showInformation(String title, String message){
       alert = new Alert(Alert.AlertType.INFORMATION);
       setMessage(title, message);
    }
    
    /**
     * Shows error dialog to user with specified title and message.
     * @param title String title of the message
     * @param message String content of the message
     */
    public static void showError(String title, String message){
       alert = new Alert(Alert.AlertType.ERROR);
       setMessage(title, message);
    }
    
    /**
     * Sets alert parameters so it can be created.
     * @param title String title of the message
     * @param message String content of the message
     */
    private static void setMessage(String title, String message){
       alert.initStyle(StageStyle.UTILITY);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.showAndWait();
    }
    
}

package pl.lipiec.passwordmanagerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main App class generated automatically.
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginPanel"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method setting root pane to the application.
     * @param fxml path to fxml file
     * @throws IOException exception thrown 
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main program method.
     * @param args starting parameters
     */
    public static void main(String[] args) {
        launch();
    }

}
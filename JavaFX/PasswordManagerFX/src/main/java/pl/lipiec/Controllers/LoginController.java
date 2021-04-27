package pl.lipiec.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.lipiec.Models.LoginModel;
import pl.lipiec.passwordmanagerfx.App;
import pl.lipiec.ViewExtensions.MessageBox;

/**
 * Class controlling login process and login view.
 * @author Krzysztof Lipiec
 * @version 1.2
 */
public class LoginController {
   
   /**
    * Instance of LoginModel model.
    */
   private final LoginModel model;     
   
   /**
    * TextField representing input of user login.
    */
   @FXML
   private TextField loginField;
   
   /**
    * TextField representing input of user password.
    */
   @FXML
   private PasswordField passwordField;
   
   /**
    * Button login confirmation.
    */
   @FXML
   private Button loginButton;
   
   /**
    * Button that moves user to registration panel.
    */
   @FXML
   private Button registerButton;
   
   /**
    * Method that calls new view.
    * Called view relates to RegistrationPanel.
    */
   @FXML
   private void callRegisterPanel() throws IOException{
           App.setRoot("RegistrationPanel");
   }

   /**
    * Controller constructor.
    * Creates new LoginModel and sets current view.
    */
   public LoginController(){
       model = new LoginModel();
   }
   
   /**
    * Action method responding to click on loginButton.
    * Calls checkCredentials method with loginField and passwordField content.
    */
   public void loginButtonAction(){
       checkCredentials(loginField.getText(), passwordField.getText());
   }
   
   /**
    * Method that checks if credential given by user in LoginPanel (view) are correct.
    * If credentials are correct and user exists, then method sets new window in App.
    * Next updated view is MainPanel where user can see his data.
    * @param login    login inserted by user in LoginPanel
    * @param password password inserted by user in LoginPanel
    */
   public void checkCredentials(String login, String password){
        
        model.setLogin(login);
        if(model.getCredentials() && password.equals(model.getPassword()) && login.equals(model.getLogin())){
            model.setPassword(password);
            BaseController.setCurrentUser(model.getUser());
            MessageBox.showInformation("Sukces", "Poprawne logowanie");
            try {
                App.setRoot("MainPanel");
            } catch (IOException ex) {
                MessageBox.showError("Błąd", ex.getMessage());
            }
        }
        else{
            MessageBox.showInformation("Błąd", "Nieudane logowanie");
            try {
                App.setRoot("LoginPanel");
            } catch (IOException ex) {
                MessageBox.showError("Błąd", ex.getMessage());
            }
        }
   }
}

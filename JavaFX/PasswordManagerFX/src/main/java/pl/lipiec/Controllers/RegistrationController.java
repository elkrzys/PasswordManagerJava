package pl.lipiec.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.lipiec.Exceptions.InvalidPasswordConfirmationEx;
import pl.lipiec.Exceptions.RegistrationFailedEx;
import pl.lipiec.Models.RegistrationModel;
import pl.lipiec.Models.User;
import pl.lipiec.passwordmanagerfx.App;
import pl.lipiec.ViewExtensions.MessageBox;

/**
 * Registration controller.
 * Class providing methods for handling user registration between the interface and registration model.
 * @author Krzysztof Lipiec
 * @version 1.2
 */
public class RegistrationController extends LoginController{
    
    /**
     * TextField for user login input.
     */
    @FXML
    private TextField loginField;
    /**
     * PasswordField for user password input.
     */
    @FXML
    private PasswordField passwordField1;
    /**
     * PasswordField for user password input (confirmation).
     */
    @FXML
    private PasswordField passwordField2;
    
    /**
     * Current model instance.
     */
   private final RegistrationModel model;

   /**
    * Creates new ReistrationModel.
    */
   public RegistrationController(){
        model = new RegistrationModel(); 
   }
   
   /**
    * Method returning to LoginPanel.
    * Action method called when user clicks on button.
    */
   @FXML
   private void backToLoginPanel(){
       try{
           App.setRoot("LoginPanel");
       }catch(IOException ex){
           MessageBox.showError("Błąd", ex.getMessage());
       }
   }
   
   /**
    * Action method responding on registerButton click.
    * It reads user input and invokes checkPasswordFields method.
    * If passwords are equal, method registerUserFromInput is invoked.
    */
   public void registerButtonAction(){
       String login = loginField.getText();
       String passwordFirst = passwordField1.getText();
       String passwordSecond = passwordField2.getText();
       try{
           checkPasswordFields(passwordFirst, passwordSecond);
           registerUserFromInput(login, passwordFirst);
       }catch(InvalidPasswordConfirmationEx ex){
           MessageBox.showWarning("Niepowodzenie", ex.getMessage());
       }     
   }
   
   /**
    * Method checking if password confirmation inserted by user is correct.
    * @param passwordOne String password
    * @param passwordTwo String password confirmation
    * @return true if passwords correct, exception otherwise
    * @throws pl.lipiec.Exceptions.InvalidPasswordConfirmationEx when passwords are not equal
    */
   public boolean checkPasswordFields(String passwordOne, String passwordTwo) throws InvalidPasswordConfirmationEx{
       if(!passwordOne.equals(passwordTwo)) throw new InvalidPasswordConfirmationEx("Hasła muszą się zgadzać");
       else return true;
   }
   
   /**
    * Method reading user input and setting new user in the model.
    * After that method tries registration by registerUser() from RegistrationModel.
    * If registration is correct, popup is shown and new user is saved to the file.
    * If not, the popup of error is shown.
    * @param login new user login
    * @param password new user password
    */
   public void registerUserFromInput(String login, String password){       
       try{    
           model.setUser(new User());
           model.getUser().setLogin(login);
           model.getUser().setPassword(password);
           
           model.registerUser();
           MessageBox.showInformation("Sukces", "Rejestracja udana");
           model.getUsers().saveJSONFile(BaseController.userListFilepath);
           model.createUserFile(model.getUser().getFilepath());
       }catch(RegistrationFailedEx ex){
           MessageBox.showWarning("Niepowodzenie", ex.getMessage());
       }
   }   
}

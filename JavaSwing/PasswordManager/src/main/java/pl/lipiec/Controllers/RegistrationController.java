package pl.lipiec.Controllers;

import static java.lang.System.err;
import javax.swing.JOptionPane;
import pl.lipiec.Exceptions.InvalidPasswordConfirmationEx;
import pl.lipiec.Exceptions.RegistrationFailedEx;
import pl.lipiec.Model.RegistrationModel;
import pl.lipiec.Model.User;
import pl.lipiec.View.RegisterPanel;

/**
 * Registration controller.
 * Class providing methods for handling user registration between the interface and registration model.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class RegistrationController {
    /**
     * Current view instance.
     */
   private final RegisterPanel view;
    /**
     * Current model instance.
     */
   private final RegistrationModel model;

   /**
    * Constructor setting current view instance.
    * Creates new ReistrationModel.
    * @param view RegisterPanel object
    */
   public RegistrationController(RegisterPanel view){
        this.view = view;
        model = new RegistrationModel(); 
   }
   
   /**
    * Method checking if password confirmation inserted by user is correct.
    * @param password_1 String password
    * @param password_2 String password confirmation
    * @return true if passwords correct, false otherwise
     *@throws pl.lipiec.Exceptions.InvalidPasswordConfirmationEx when passwords
    */
   public boolean checkPasswordFields(String password_1, String password_2) throws InvalidPasswordConfirmationEx{
       if(!password_1.equals(password_2)) throw new InvalidPasswordConfirmationEx("Hasła muszą się zgadzać");
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
       model.setUser(new User());
       model.getUser().setLogin(login);
       model.getUser().setPassword(password);
       
       try{
           model.registerUser();
           JOptionPane.showMessageDialog(null, "Zarejestrowano", "Sukces", JOptionPane.INFORMATION_MESSAGE);
           model.getUsers().saveJSONFile(BaseController.userListFilepath);
           model.createUserFile(model.getUser().getFilepath());
       }catch(RegistrationFailedEx ex){
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Błąd", JOptionPane.INFORMATION_MESSAGE);
       }
   }
   
   
}

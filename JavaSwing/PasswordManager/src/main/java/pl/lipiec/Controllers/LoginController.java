package pl.lipiec.Controllers;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pl.lipiec.Model.LoginModel;
import pl.lipiec.View.LoginPanel;
import pl.lipiec.View.MainPanel;

/**
 * Class controlling login process and login view.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class LoginController {
   
   /**
    * Instance of LoginPanel view.
    */
   private final LoginPanel view;
   /**
    * Instance of LoginModel model.
    */
   private final LoginModel model;

   /**
    * Controller constructor.
    * Creates new LoginModel and sets current view.
    * @param view LoginPanel view
    */
   public LoginController(LoginPanel view){
        this.view = view;
        model = new LoginModel();  
   }
   
   /**
    * Method that checks if credential given by user in LoginPanel (view) are correct.
    * If credentials are correct and user exists, then method uses BaseController to update window.
    * Next updated view is MainPanel where user can see his data.
    * @param login    login inserted by user in LoginPanel
    * @param password password inserted by user in LoginPanel
    */
   public void checkCredentials(String login, String password){
        model.setLogin(login);
        if(model.getCredentials() && password.equals(model.getPassword()) && login.equals(model.getLogin())){
            model.setPassword(password);
            
            JOptionPane.showMessageDialog(null, "Poprawne logowanie", "Sukces", JOptionPane.INFORMATION_MESSAGE);
            BaseController.setCurrentUser(model.getUser());
            BaseController.updateView(new MainPanel());
        }
        else{
            JOptionPane.showMessageDialog(null, "Błędne logowanie", "Uwaga", JOptionPane.INFORMATION_MESSAGE);
            BaseController.updateView(new LoginPanel());
        }
   }
   
   /**
    * Method that calls new view.
    * Called view relates to RegistrationPanel.
    * @param view JPanel object
    */
   public void callRegisterPanel(JPanel view){
       BaseController.setView(view);
   }
  
}

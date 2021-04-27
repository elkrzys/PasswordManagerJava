package pl.lipiec.passwordmanager;
import pl.lipiec.Controllers.BaseController;
import pl.lipiec.View.LoginPanel;

/**
 * Main class starting program with new BaseController and LoginPanel.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class Main {
    
    public static void main(String args[]){
        
        BaseController.BaseController();
        BaseController.setView(new LoginPanel());

    }
}

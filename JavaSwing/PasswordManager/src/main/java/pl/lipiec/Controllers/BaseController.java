package pl.lipiec.Controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.lipiec.Model.User;

/**
 *
 * Singleton base controller used to handle views and controllers
 * This class consists of static fields and methods.
 * Contains instance of current window, view and user logged to the program.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class BaseController {
    /**
     * 
     * Single BaseController instance
     */
    private static BaseController base_instance = null;
    
    /**
     * Single window and view that is changed
     */
    private static JFrame window;
    /**
     * Current seen view panel attached to the JFrame window
     */
    private static JPanel currentView;
    /**
     * User that is currently logged
     */
    private static User currentUser;
    
    /**
     * Global path to file containing multiple users.
     * It has users id, login, password and personal accounts filepath.
     */
    public static String userListFilepath = System.getProperty("user.dir") + "\\users.json";
    public static String personalAccountsListPath = System.getProperty("user.dir") + "\\";
    /**
     * Base non-argument constructor.
     * Creates new window with title, location and size
     */
    private BaseController(){
        window = new JFrame("Menadżer haseł");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(new Dimension(600, 400));
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        // create usersList file if not exist
        File usersFile = new File(userListFilepath);
        if(usersFile.length() == 0){
            try{
                usersFile.createNewFile();
                try (FileWriter writer = new FileWriter(usersFile)) {
                    writer.write("[ { } ]");
                    writer.close();
                }
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }        
        }
        
    }
    /**
     * BaseController singleton method.
     * @return BaseController single instance
     */
    public static BaseController BaseController(){
         if (base_instance == null) 
        { 
            base_instance = new BaseController(); 
        } 
         return base_instance; 
    }
    
    /**
     * Method returns User object of currently logged user
     * @return currentUser
     */
    public static User getCurrentUser() {
        return currentUser;
    }
    /**
     * Method sets currently logged user.
     * @param user is currently logged user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    /**
     * Method uses view param to set current window content and makes window visible
     * @param view is new JPanel object.
     */
    public static void setView(JPanel view){
        BaseController.currentView = view;
        BaseController.window.setContentPane(currentView);
        BaseController.window.setVisible(true);
    }
    
    /**
     * Method removes all content from window and invokes setView with new JPanel view
     * @param view updated Jpanel view
     */
    public static void updateView(JPanel view){
        BaseController.window.getContentPane().removeAll();
        BaseController.setView(view);
    }
}

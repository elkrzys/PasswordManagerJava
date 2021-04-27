package pl.lipiec.Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import pl.lipiec.Models.User;

/**
 *
 * Singleton base controller used to handle current user and filepaths.
 * This class consists of static fields and methods.
 * Contains instance of current window, view and user logged to the program.
 * @author Krzysztof Lipiec
 * @version 1.3
 */
public class BaseController {
    /**
     * 
     * Single BaseController instance
     */
    private static BaseController base_instance = null;

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
     */
    private BaseController(){ 
    
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
}

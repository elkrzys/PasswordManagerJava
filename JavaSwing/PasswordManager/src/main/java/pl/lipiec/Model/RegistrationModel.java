package pl.lipiec.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import pl.lipiec.Controllers.BaseController;
import pl.lipiec.Exceptions.RegistrationFailedEx;

/**
 * Class extends LoginModel to use fields and methods provided by this model.
 * Registers new user in users file and creates user personal accounts file.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class RegistrationModel extends LoginModel{
    
    /**
     * This method looks for a user in user list by using method findUser.If user is not found, then new one is registered by creating his file.
     * New user has id equal to the last user id + 1, or if its the first user, equal to 1.
     * User is added to the list of users.
     * @throws pl.lipiec.Exceptions.RegistrationFailedEx
     */
    public void registerUser() throws RegistrationFailedEx{

           if(findUser() != null) throw new RegistrationFailedEx("Użytkownik już istnieje");
           else{
            int id = (users.getUsers().size() > 0) ? getUsers().getLastUserId() + 1 : 1;
            String filepath = BaseController.personalAccountsListPath + "z" + Integer.toString(id) + user.getLogin() + "file.json";
            user.setId(id);
            user.setFilepath(filepath);
            getUsers().addUser(user);
           }        
    }
    
    /**
     * Creates new file of user data.
     * Writes new empty JSONObject in JSONArray as simple String.
     * "[ {} ]"
     * @param filepath source filepath to the new file 
     */
    public void createUserFile(String filepath){
            File userFile = new File(filepath);
            try{
                userFile.createNewFile();
                try (FileWriter writer = new FileWriter(userFile)) {
                    writer.write("[ { } ]");
                    writer.close();
                }
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }          
    }
    
}

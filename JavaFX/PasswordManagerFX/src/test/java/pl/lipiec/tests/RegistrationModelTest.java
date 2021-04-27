package pl.lipiec.tests;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import pl.lipiec.Exceptions.RegistrationFailedEx;
import pl.lipiec.Models.RegistrationModel;
import pl.lipiec.Models.User;
import pl.lipiec.Models.Users;

/**
 * Class testing user registration model (RegistrationModel class methods).
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class RegistrationModelTest {
    
    public RegistrationModelTest() {
    }
    
    RegistrationModel model = new RegistrationModel();
    Users users = new Users();
    /**
     * Correct testing path to user list file.
     */
    private final String correctUsersPath = System.getProperty("user.dir") + "\\testUsers.json";
    /**
     * Empty testing path to user list file.
     */
    private final String emptyFilePath = System.getProperty("user.dir") + "\\emptylist.json";
    
    /**
     * Tests creating file for new user. File is created as new empty JSONArray containing empty JSONOBject.
     */
    @Test
    public void testCreateUserFile(){
        try{
            model.createUserFile(emptyFilePath);
            assertTrue(true);
            File file = new File(emptyFilePath);
            try{  
                file.delete();
            }catch(Exception ex){
                fail("Deleting file failed");
            }
        }
        catch(Exception ex){
            fail("Exception was thrown" + ex.getMessage());
        }
    }
    
    /**
     * Tests correct registration of new user.
     */
    @Test
    public void testRegisterUserOk(){
        users = new Users(emptyFilePath);
        model.setUser(new User(100000, "new", "new", "path.json"));
        try{
           model.registerUser();
           assertTrue(true);
        }catch(RegistrationFailedEx ex){
            fail(ex.getMessage());
        }
    }
    
    /**
     * Tests if registration fails when user trying to registrate already exists.
     */
    @Test
    public void testRegisterExistingUser(){
        users.createUserList(correctUsersPath);
        User existingUser = users.getUsers().get(0);
        model.setUser(existingUser);
        try{
            model.registerUser();
            fail("Exception RegistrationFailedEx should be thrown");
        }catch(RegistrationFailedEx ex){
            assertTrue(true);
        }
    }
    /**
     * Tests if registration fails when user trying to registrate already exists.
     * @param param expected null User object
     */
    @ParameterizedTest
    @NullSource
    public void testRegistrationFailed(User param){
        users.createUserList(correctUsersPath);
        model.setUser(param);
        try{
            model.registerUser();
            fail("Exception RegistrationFailedEx should be thrown");
        }catch(Exception ex){
            assertTrue(true);
        }
    }
}
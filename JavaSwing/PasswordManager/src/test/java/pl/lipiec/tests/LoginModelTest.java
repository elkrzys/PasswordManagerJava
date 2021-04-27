/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lipiec.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.lipiec.Model.LoginModel;
import pl.lipiec.Model.User;
import pl.lipiec.Model.Users;

/**
 * Class providing tests to LoginModel class.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class LoginModelTest {
    
    public LoginModelTest() {
    }
    
    LoginModel model = new LoginModel();
    String path = System.getProperty("user.dir");
    
    /**
     * Tests if method getCredentials() works correctly when user exists in file.
     */
    @Test
    public void testGetCredentialsOk(){
        var user = new User(111, "Testowy", "test", "testpath.json");
        model.setUser(user);
        model.getUsers().addUser(user);
        // file filled with users
        model.getUsers().saveJSONFile(path + "testUsers.json");
        
        assertTrue(model.getCredentials());
    }
    
    /**
     * Tests if method getCredentials() returns false when user trying to log in doesen't exist.
     */
    @Test
    public void testGetCredentialsFailed(){
        var user = new User(34, "Nie ma takiego usera", "testowo", "filepath.json");
        model.setUser(user);

        assertFalse(model.getCredentials());
    }
    
    /**
     * Testing method findUser().
     * If user exists in the list, then method should return User object.
     */
    @Test
    public void testFindUser(){
        // first user from list of users
        model.getUsers().createUserList(path + "testUsers.json");
        var user = model.getUsers().getUsers().get(0);
        model.setUser(user);
        
        assertNotNull(model.findUser());
    }
    
    /**
     * Testing method findUser().
     * If user exists in the list, then method should return User object.
     */
    @Test
    public void testFindUserFailed(){
        // first user from list of users
        Users emptyList = new Users(path + "emptylist.json");
        model.getUsers().setUsers(emptyList.getUsers());
        var user = new User(999, "added user", "hello", "testfilepath.json");
        model.setUser(user);
        
        assertNull(model.findUser());
    }
    
    
}

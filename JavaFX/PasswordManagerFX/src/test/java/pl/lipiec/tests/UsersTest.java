package pl.lipiec.tests;

import java.io.File;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.lipiec.Models.User;
import pl.lipiec.Models.Users;

/**
 * Class providing tests for Users class.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class UsersTest {
    
    public UsersTest() {
    }
    
    private Users users = new Users();
    private final String correctUsersPath = System.getProperty("user.dir") + "\\testUsers.json";
    
    /**
     * Tests reading JSON file which is prepared.
     * Correct JSON filepath should be in field correctUsersPath.
     */
    @Test
    public void testReadJSONFile(){
        try{
            users.readJSONFile(correctUsersPath);
            assertTrue(true);
        }
        catch(Exception ex){
            fail("Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Test if list of users is created correctly by reading file.
     */
    @Test
    public void testCreateUserListOk(){
        try{
            users.createUserList(correctUsersPath);
            assertTrue(true);
        }catch(Exception ex){
            assertTrue(false);
        }
    }
    
    /**
     * Checks if exception is thrown when file is not correct.
     * @param param filepath to the wrong or not existing file
     */
    @ParameterizedTest
    @ValueSource(strings = {
        "/notexistingfile.json",
        "/txt.json",
        "/broken.json"})
    public void testReadJSONFileFailed(String param){
        //users = new Users();
        try{
            users.readJSONFile(System.getProperty("user.dir") + param);
            fail("Exception should appear");
        }catch(Exception ex){
            assertTrue(true);
        }
    }
     
    /**
     * Parameterized test which provides multiple users to check if adding them to the list is working correctly.
     * Arguments are provided by method UsersParamProvider(),
     * which returns Stream of arguments (int id, String login, String password, String filepath).
     * @param id user id
     * @param login user login
     * @param password user password
     * @param filepath user filepath
     */
    @ParameterizedTest
    @MethodSource("UsersParamProvider") 
    public void testAddUser(int id, String login, String password, String filepath){
        // Testing file filled with example users.
        users = new Users(correctUsersPath);
        var currentSize = users.getUsers().size();
        var expectedSize = currentSize + 1;
        
        users.addUser(new User(id, login, password, filepath));
        currentSize = users.getUsers().size();
        assertEquals(expectedSize, currentSize);
    }
    static Stream<Arguments> UsersParamProvider() {
        return Stream.of(
        arguments(1, "TestCreatedUser1", "User1Password", "User1Filepath.json"),
        arguments(2, "TestCreatedUser2", "User2Password", "User2Filepath.json"),
        arguments(3, "TestCreatedUser3", "User3Password", "User3Filepath.json"));
    } 
    
    /**
     * Tests method that gets id of the last user in the list.
     */
    @Test
    public void testGetLastUserId(){
        users = new Users(correctUsersPath);
        var expectedId = users.getUsers().get(users.getUsers().size() - 1).getId();
        var currentId = users.getLastUserId();
        
        assertEquals(expectedId, currentId);
    }
    
    /**
     * Test saving users list in file, which the list was read from.
     */
    @Test
    public void testSaveUsersListOk(){
        users = new Users(correctUsersPath);
        try{
            users.saveJSONFile(correctUsersPath);
            assertTrue(true);
        }
        catch(Exception ex){
            assertTrue(false);
        }
    }
    
    /**
     * Test saving users list in file that not exists. New file should be created.
     * After test, new created file is deleted.
     */
    @Test
    public void testSaveUsersListInNewFile(){
        var newFile = System.getProperty("user.dir") + "\\newFileTest.json";
        users = new Users(newFile);
        try{
            users.saveJSONFile(newFile);
            assertTrue(true);
            File file = new File(newFile);
            try{
                file.delete();
            }catch(Exception ex){
                fail("File delete exception");
            }
        }
        catch(Exception ex){
            assertTrue(false);
        }
    }
}

package pl.lipiec.Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pl.lipiec.Controllers.BaseController;

/**
 * Model containing list of users.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class Users {
    /**
     * List of user objects.
     */
    private List<User> users;
    
    /**
     * Default empty constructor.
     */
    public Users(){}
    
    /**
     * Constructor initializing new ArrayList of users.
     * After initialization list is read from file.
     * @param filename source string 
     */
    public Users(String filename) {
        createUserList(BaseController.userListFilepath);
    }
    
    /**
     * User list getter.
     * @return list of User objects
     */
    public List<User> getUsers() {
        return users;
    }
    
    /**
     * User list setter.
     * @param users new list of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    /**
     * Adds new user to the list.
     * @param user is a new User object.
     */
    public void addUser(User user){
        this.users.add(user);
    }
    
    /**
     * Method gets id of the last user in list.
     * @return id
     */
    public int getLastUserId(){
        return this.users.get(users.size() -1).getId();
    }
    
    /**
     * Method that creates a JSON file reader and parser from source file.
     * @param filename source file name or path
     * @return Object that later needs a cast to particular JSON format object.
     * @throws Exception when reading or parsing goes wrong, exception caught in main read method.
     */
    public Object readJSONFile(String filename) throws Exception{
         FileReader reader = new FileReader(filename);
         JSONParser jsonParser = new JSONParser();
         return jsonParser.parse(reader);
    }
    
    /**
     * Method reads list of current users from file.
     * It initializes users as new ArrayList.
     * This method uses readJSONFile to parse .json file.
     * It reads data in given format:
     * [
     *  {
     *      "id":1,
     *      "login":"string",
     *      "password":"string"
     *  }
     * ]
     * Square bracket is representing the JSONArray.
     * Curly brackets are representich JSONObject which can be placed after ,
     * [{JSONObject},{JSONObject}]
     * Method catches the Exception from readJSONFile.
     * @param filename source file name or path
     */
    public void createUserList(String filename){
        try {
            JSONArray array = (JSONArray) readJSONFile(filename);
            this.users = new ArrayList<User>();
            
            for(Object o : array){
                JSONObject jUser = (JSONObject) o;

                User user = new User(
                    (int) (long)jUser.get("id"),
                    (String) jUser.get("login"),
                    (String) jUser.get("password"),
                    (String) jUser.get("filepath")
                );
                this.users.add(user);
            }
        } catch (Exception ex) {
            System.out.println("An error occurred.");
            //ex.printStackTrace();
        }   
    }
    
    /**
     * Method saving user list (ArrayList) to the JSON file.
     * Final file is an JSONArray consisting of JSONObject.
     * JSONObject and JSONArray are unnamed.
     * Example format of output is
     * [
     *  {
     *      "id":"string",
     *      "accountName":"string",
     *      "description":"string",
     *      "platformName":"string",
     *  }
     * ]
     * Square bracket is representing the JSONArray.
     * Curly brackets are representich JSONObject which can be placed after ,
     * [{JSONObject},{JSONObject}]
     * @param filename path or name of target file
     */
    public void saveJSONFile(String filename){
        try (FileWriter file = new FileWriter(filename)) {
            JSONArray jsonArray = new JSONArray();
            for(var user : users){
                JSONObject userObject = new JSONObject();
                userObject.put("id", user.getId());
                userObject.put("login", user.getLogin());
                userObject.put("password", user.getPassword());
                userObject.put("filepath", user.getFilepath());
                jsonArray.add(userObject);
            }
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    
    
}

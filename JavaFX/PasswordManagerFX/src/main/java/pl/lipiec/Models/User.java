package pl.lipiec.Models;

/**
 * User model class.
 * This class contains of user id, login, password and filepath.
 * Id is represented by integer, the rest are Strings.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class User {
    /**
     * User id.
     */
    private int id;
    /**
     * User login.
     */
    private String login;
    /**
     * User password.
     */
    private String password;
    /**
     * Filepath to user data source file.
     */
    private String filepath;
    
    /**
     * User constructor, sets given values to new User object.
     * @param id int representing user id.
     * @param login String representing user login.
     * @param password String representing user password.
     * @param filepath source filename of user personal accounts.
     */
    public User(int id, String login, String password, String filepath) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.filepath = filepath;
    }
    
    /**
     * Default constructor.
     * id = 0
     * login = ""
     * password = ""
     * filepath = ""
     */
    public User() {
        this.id = 0;
        this.login = "";
        this.password = "";
        this.filepath = "";
    }
    
    /**
     * Gets user personal accounts filepath.
     * @return filepath String
     */
    public String getFilepath() {
        return filepath;
    }
    
    /**
     * Sets user personal accounts filepath.
     * @param filepath new filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * User id getter.
     * @return id as integer
     */
    public int getId() {
        return id;
    }
    
     /**
     * User login getter.
     * @return id as String
     */
    public String getLogin() {
        return login;
    }

     /**
     * User password getter.
     * @return id as String
     */
    public String getPassword() {
        return password;
    }
    
     /**
      * User id setter.
      * @param id is new int representing user id.
      */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
      * User login setter.
      * @param login is new String representing user login.
      */
    public void setLogin(String login) {
        this.login = login;
    }
    
    /**
      * User password setter.
      * @param password is new String representing user password.
      */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}

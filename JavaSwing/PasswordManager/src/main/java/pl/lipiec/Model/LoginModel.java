package pl.lipiec.Model;


import pl.lipiec.Controllers.BaseController;

/**
 * Model class representing fields and methods needed to log in the program.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class LoginModel {
    /**
     * Current user attempting to log in
     */
    protected User user = new User();
    
    /**
     * List of users pulled from file.
     */
    protected Users users = new Users(BaseController.userListFilepath);
    
    /**
     * Getter of users list.
     * @return users list
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Current user getter.
     * @return user current user object.
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Current user setter.
     * @param user new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     * Current user id getter.
     * @return id of current user.
     */
    public int getId() {
        return this.user.getId();
    }
    
    /**
     * Current user login getter.
     * @return login as string
     */
    public String getLogin() {
        return this.user.getLogin();
    }

    /**
     * Current user password getter.
     * @return password as string
     */
    public String getPassword() {
        return this.user.getPassword();
    }
    
    /**
     * Sets current user id passed as argument.
     * @param id integer representig id number
     */
    public void setId(int id) {
        this.user.setId(id);
    }
    
    /**
     * Sets current user login passed as argument.
     * @param login user login
     */
    public void setLogin(String login) {
        this.user.setLogin(login);
    }
    
    /**
     * Sets current user id passed as argument.
     * @param password user password
     */
    public void setPassword(String password) {
        this.user.setPassword(password);
    }
    
    /**
     * Sets user in the model.
     * Method uses findUser() method to find a user in the list.
     * If user is found in users (list), method sets user in model.
     * @return true if user was found in list.
     */
    public boolean getCredentials(){
        return ((user = findUser()) != null);
    }
    
    /**
     * Finds current user in user list.
     * Only login is compared.
     * If user is found in list, found User object is returned.
     * If not found, method returns null.
     * @return user or null
     */
    public User findUser(){    
        User found;
        found = users.getUsers().stream()
                .filter(u -> {
                return this.user.getLogin().equals(u.getLogin());
                })
                .findAny()
                .orElse(null);

        return found;
    }
}

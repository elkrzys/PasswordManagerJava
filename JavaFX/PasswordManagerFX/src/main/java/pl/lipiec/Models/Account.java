package pl.lipiec.Models;

import javafx.beans.property.SimpleStringProperty;
import org.json.simple.JSONObject;

/**
 * Single account container for password management.
 * Contains SimpleStringProperty fields.
 * @author Krzysztof Lipiec
 * @version 1.2
 */
public class Account {

    /**
     * Name of platform such as "Gmail".
     */
    private SimpleStringProperty platformName;
    /**
     * Description of account.
     */
    private SimpleStringProperty description;
    /**
     * Account name, mail, login, id etc.
     */
    private SimpleStringProperty accountName;
    /**
     * Password to the account.
     */
    private SimpleStringProperty password;
    
    /**
     * Implementation of function comparing to string
     */
    private final CompareInterface cmpStrings = (Account a, Account b) ->
                    a.platformName.get().equals(b.platformName.get()) &&
                    a.description.get().equals(b.description.get()) &&
                    a.accountName.get().equals(b.accountName.get()) &&
                    a.password.get().equals(b.password.get());
                      
    
    /**
     * Constructor with all parameters needed to set up the Account object.
     * 
     * @param platform String name of platform
     * @param descr String description
     * @param account String account name, login, mail etc.
     * @param passw String password to the platform
     */
    public Account(String platform, String descr, String account, String passw) {
        platformName = new SimpleStringProperty(platform);
        description = new SimpleStringProperty(descr);
        accountName = new SimpleStringProperty(account);
        password = new SimpleStringProperty(passw);
    }
    
    /**
     * Setter of platformName field.
     * @param platformName String argument setting name of the platform
     */
    public void setPlatformName(String platformName) {
        this.platformName.set(platformName);
    }
    
    /**
     * Setter of accountName field.
     * @param accountName String argument setting name of the account
     */
    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }
    
    /**
     * Getter of description field.
     * @return description
     */
    public String getDescription() {
        return description.get();
    }
    
    /**
     * Setter of description field.
     * @param description String argument setting description.
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    /**
     * Setter of password field.
     * @param password String argument setting password.
     */
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    /**
     * Getter of platformName field.
     * @return platformName
     */
    public String getPlatformName() {
        return platformName.get();
    }
    
    /**
     * Getter of accountName field.
     * @return accountName
     */
    public String getAccountName() {
        return accountName.get();
    }
    
    /**
     * Getter of password field.
     * @return password
     */
    public String getPassword() {
        return password.get();
    }
    
    
    /**
     * Overriden equals method that this object to the given one.
     * @param obj Account object to compare.
     * @return true if objects are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }

    Account other = (Account) obj;
    
    return cmpStrings.cmp(this, other);
    }
    
    
    /**
     * Converts object to JSON format.
     * Needs maven dependecy for json-simple.
     * https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1
     * @return JSONObject
     */
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("platformName", platformName.get());
        obj.put("description", description.get());
        obj.put("accountName", accountName.get());
        obj.put("password", password.get());
        return obj;
    }
}

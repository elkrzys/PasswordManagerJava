package pl.lipiec.Model;

import org.json.simple.JSONObject;

/**
 * Single account container for password management
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class Account {

    /**
     * Name of platform such as "Gmail".
     */
    private String platformName;
    /**
     * Description of account.
     */
    private String description;
    /**
     * Account name, mail, login, id etc.
     */
    private String accountName;
    /**
     * Password to the account.
     */
    private String password;
    
    /**
     * Implementation of function comparing to string
     */
    private final CompareInterface cmpStrings = (Account a, Account b) ->
                    a.platformName.equals(b.platformName) &&
                    a.description.equals(b.description) &&
                    a.accountName.equals(b.accountName) &&
                    a.password.equals(b.password);
                      
    
    /**
     * Constructor with all parameters needed to set up the Account object.
     * 
     * @param platform String name of platform
     * @param descr String description
     * @param account String account name, login, mail etc.
     * @param passw String password to the platform
     */
    public Account(String platform, String descr, String account, String passw) {
        this.platformName = platform;
        this.description = descr;
        this.accountName = account;
        this.password = passw;
    }
    
    /**
     * Setter of platformName field.
     * @param platformName String argument setting name of the platform
     */
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    
    /**
     * Setter of accountName field.
     * @param accountName String argument setting name of the account
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    /**
     * Getter of description field.
     * @return description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Setter of description field.
     * @param description String argument setting description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Setter of password field.
     * @param password String argument setting password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Getter of platformName field.
     * @return platformName
     */
    public String getPlatformName() {
        return platformName;
    }
    
    /**
     * Getter of accountName field.
     * @return accountName
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     * Getter of password field.
     * @return password
     */
    public String getPassword() {
        return password;
    }
    
    
    /**
     * Overriden equals method that this object to the given one.
     * @param obj Account object to compare.
     * @return true if objects are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

    if (obj == this) {
        return true;
    }

    if (!(obj instanceof Account)) {
        return false;
    }

    Account other = (Account) obj;
    
    return cmpStrings.cmp(this, other);
    
//    return cmpStrings.cmp(this.getPlatformName(), other.platformName) && cmpStrings.cmp(this.getPlatformName(), other.platformName)
//            && cmpStrings.cmp(this.getPlatformName(), other.platformName) && cmpStrings.cmp(this.getPlatformName(), other.platformName);
    
//    return platformName == other.platformName && description == other.description &&
//            accountName == other.accountName && password == other.password;
    }
    
    
    /**
     * Converts object to JSON format.
     * Needs maven dependecy for json-simple.
     * https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1
     * @return JSONObject
     */
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("platformName", platformName);
        obj.put("description", description);
        obj.put("accountName", accountName);
        obj.put("password", password);
        return obj;
    }
}

package pl.lipiec.tests;

import java.util.stream.Stream;
import org.json.simple.JSONObject;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lipiec.Models.Account;

/**
 * User class tests.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class AccountTest {
    
    public AccountTest() {
    }    
    
    private Account account;
    
    /**
     * Tests inner comparator.
     */
    @Test
    public void testEqualObjects(){
        account = new Account("newPlatform", "description of platform", "newAccount", "newPassword");
        Account equalObject = account;
        
        assertTrue(account.equals(equalObject));
    }
    
    /**
     * Tests inner comparator with objects not equal objects.
     */
    @Test
    public void testNotEqualObjects(){
        account = new Account("newPlatform", "description of platform", "newAccount", "newPassword");
        Account equalObject = new Account("platformOld", "descr", "acc", "passwrd");
        
        assertFalse(account.equals(equalObject));
    }
    
    /**
     * Tests inner comparator with objects which differ on a single field.
     * @param another Account object to compare returned from Stream
     */
    @ParameterizedTest
    @MethodSource("AccountsParamProvider")
    public void testNotEqualObjectFields(Account another){
        account = new Account("newPlatform", "description of platform", "newAccount", "newPassword");
        assertFalse(account.equals(another));
    }
    static Stream<Account> AccountsParamProvider() {
        return Stream.of(
        new Account("anotherPlatform", "description of platform", "newAccount", "newPassword"),
        new Account("newPlatform", "another description", "newAccount", "newPassword"),
        new Account("newPlatform", "description of platform", "anotherAccount", "newPassword"),
        new Account("newPlatform", "description of platform", "anotherAccount", "anotherPassword"));
    } 
    
    /**
     * Tests if conversion from object to JSONObject was correct.
     */
    @Test
    public void testToJSONConversion(){
        
        String platform = "newPlatform";
        String description = "description of platform";
        String accountField = "newAccount";
        String password = "newPassword";
        
        account = new Account(platform, description, accountField, password);
        JSONObject obj = account.toJSON();
        
        assertEquals(platform, (String) obj.get("platformName"));
        assertEquals(description, (String) obj.get("description"));
        assertEquals(accountField, (String) obj.get("accountName"));
        assertEquals(password, (String) obj.get("password"));
    }
    
}


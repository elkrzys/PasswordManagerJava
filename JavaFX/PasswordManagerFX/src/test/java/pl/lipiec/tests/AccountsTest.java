/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lipiec.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import pl.lipiec.Models.Account;
import pl.lipiec.Models.Accounts;

/**
 * Class providing tests to Accounts class.
 * Class Accounts has list of user's personal accounts list.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class AccountsTest {
    
    public AccountsTest() {
    }

    private Accounts accounts;
    
    /**
     * Tests if reading file is possible, if not, catches exception.
     * Excepted exception.
     */
    public void testReadJSONFileNotExists(){
        String filepath = System.getProperty("user.dir") + "\\testNonExists.json";
        try{
            accounts.readJSONFile(filepath);
            fail("Exception should appear");
        }
        catch(Exception ex){
            assertTrue(true);
        }
    }
    
    /**
     * Tests if reading file created new list of accounts.
     */
    @Test
    public void testReadingFromFile(){
        var filepath = System.getProperty("user.dir") + "\\test.json";
        accounts = new Accounts();
        accounts.readAccountsFromFile(filepath);
        assertTrue(accounts.getAccountList().size() > 0);
    }
    
    /**
     * Tests if file is saved correctly.
     */
    @Test
    public void testSaveJSONFile(){
         var filepath = System.getProperty("user.dir") + "\\test.json";
         accounts = new Accounts(filepath);
         accounts.addAccount(new Account("newPlatform", "description of platform", "newAccount", "newPassword"));
         
         try{
             accounts.saveJSONFile(filepath);
         }catch(Exception ex){
             fail("Exception " + ex);
         }
     }
          
    /**
     * Tests if adding new Account to list is correct.
     * Compares size of list before removing the object, and after that.
     */
    @Test
    public void testAddingToList(){
        var filepath = System.getProperty("user.dir") + "\\test.json";
        accounts = new Accounts(filepath);
        var currentSize = accounts.getAccountList().size();
        var expectedSize = currentSize + 1;
        
        accounts.addAccount(new Account("newPlatform", "description of platform", "newAccount", "newPassword"));
        currentSize = accounts.getAccountList().size();
        
        assertEquals(expectedSize, currentSize);
    }
    
    /**
     * Tests deleting object from list.
     * Compares size of list before removing the object, and after that.
     */
    @Test
    public void testDeleteFromList(){
        var filepath = System.getProperty("user.dir") + "\\test.json";
        accounts = new Accounts(filepath);
        Account acc = new Account("testPlatform", "description of platform", "test", "testPassword");
        accounts.addAccount(acc);
        
        var currentSize = accounts.getAccountList().size();
        var expectedSize = currentSize - 1;
        
        accounts.deleteAccount(acc);
        currentSize = accounts.getAccountList().size();
        
        assertEquals(expectedSize, currentSize);
    }
}

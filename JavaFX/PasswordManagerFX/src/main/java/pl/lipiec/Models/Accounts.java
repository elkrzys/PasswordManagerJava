package pl.lipiec.Models;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Model that represents database of accounts.
 * 
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class Accounts {
    /**
     * List of current user accounts and passwords.
     */
    private List<Account> accountList;
    
    /**
     * Empty constructor.
     */
    public Accounts(){
        accountList = new ArrayList<Account>();
    }
    /**
     * Constructor creates new accountList.
     * After initialization, the list is created by reading file in readAccountsFromFile method.
     * @param filepath source filepath of user's list of accounts
     */
    public Accounts(String filepath) {
        this.accountList = new ArrayList<Account>();
        this.readAccountsFromFile(filepath);
    }
    
    /**
     * Returns the list of Account objects.
     * @return current list
     */
    public List<Account> getAccountList() {
        return accountList;
    }
    
    /**
     * Sets list of accounts to the list passed as argument.
     * @param list new list
     */
    public void setAccountList(List<Account> list) {
        this.accountList = list;
    }
    
    /**
     * Returns size of the list.
     * @return current list size
     */
    public int size(){
        return accountList.size();
    }
    
    /**
     * Returns account from list to the table of given row Index.
     * @param rowIndex index of list element
     * @return Account from the list
     */
    public Account get(int rowIndex) {
       return accountList.get(rowIndex);
    }
    
    /**
     * Adds new account object to the list.
     * @param acc new account
     */
    public void addAccount(Account acc){
        accountList.add(acc);
    }
    
    /**
     * Removes specified account from the list.
     * @param acc account to delete
     */
    public void deleteAccount(Account acc){
        accountList.remove(acc);
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
     * Method reads list of current user accounts from file.
     * It initializes accountList as new ArrayList.
     * This method uses readJSONFile to parse .json file.
     * It reads data in given format:
     * [
     *  {
     *      "password":"string",
     *      "accountName":"string",
     *      "description":"string",
     *      "platformName":"string",
     *  }
     * ]
     * Square bracket is representing the JSONArray.
     * Curly brackets are representich JSONObject which can be placed after ,
     * [{JSONObject},{JSONObject}]
     * Method catches the Exception from readJSONFile.
     * @param filename source file name or path
     */
    public void readAccountsFromFile(String filename){
        this.accountList = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) readJSONFile(filename);

            for(Object o : array){
                JSONObject jAccount = (JSONObject) o;
                if(jAccount == null) break;
                Account account = new Account(
                    (String) jAccount.get("platformName"),
                    (String) jAccount.get("description"),
                    (String) jAccount.get("accountName"),
                    (String) jAccount.get("password")
                );
                accountList.add(account);
            }
        } catch (Exception ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }   
    }
    
    /**
     * Method saving accounts list (ArrayList) to the JSON file.
     * Final file is an JSONArray consisting of JSONObject.
     * JSONObject and JSONArray are unnamed.
     * Example format of output is
     * [
     *  {
     *      "password":"string",
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
            for(var acc : accountList){
                JSONObject accountObject = new JSONObject();
                accountObject = acc.toJSON();
                jsonArray.add(accountObject);
            }
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

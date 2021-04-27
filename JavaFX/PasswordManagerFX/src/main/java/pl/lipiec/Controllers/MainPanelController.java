package pl.lipiec.Controllers;

import pl.lipiec.Models.Accounts;
import pl.lipiec.Models.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.lipiec.ViewExtensions.MessageBox;


/**
 * MainPanel Controller class.
 * It provides main actions between model and view.
 * @author Krzysztof Lipiec
 * @version 1.2
 */
public class MainPanelController {
    
    /**
     * List of user personal accounts contained in Accounts object.
     */
    private Accounts accounts;
    /**
     * List of user personal accounts represented as ObservableList.
     * Data source for TableView.
     */
    private ObservableList<Account> data;
    
    /**
     * Main table displaying user's accounts.
     */
    @FXML
    private TableView table;
    /**
     * TableColumn for platformName field.
     */
    @FXML
    private TableColumn<Account, String> platformNameCol;  
    /**
     * TableColumn for description field.
     */
    @FXML
    private TableColumn<Account, String> descriptionCol;
    /**
     * TableColumn for accountName field.
     */
    @FXML
    private TableColumn<Account, String> accountNameCol;
    /**
     * TableColumn for password field.
     */
    @FXML
    private TableColumn<Account, String> passwordCol;
    /**
     * Button calling action removeSelectedRow
     */
    @FXML
    private Button removeButton;
    /**
     * Button calling action addNewEmptyRow
     */
    @FXML
    private Button addRowButton;
    /**
     * Button calling action saveData
     */
    @FXML
    private Button saveButton;
    
    /**
     * Method initializing table data source.
     * It sets columns for the table and sets actions for buttons.
     */
    @FXML
    public void initialize(){
        accounts.getAccountList().forEach(a -> {
            data.add(a);
        });
        table.setItems(data);
        table.setVisible(true);
        table.setEditable(true);
        
        platformNameCol.setCellValueFactory(new PropertyValueFactory<Account, String>("platformName"));
        platformNameCol.setCellFactory(TextFieldTableCell.<Account>forTableColumn());
        platformNameCol.setOnEditCommit(
            (CellEditEvent<Account, String> t) -> {
                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setPlatformName(t.getNewValue());
        });
        
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Account, String>("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.<Account>forTableColumn());
        descriptionCol.setOnEditCommit(
            (CellEditEvent<Account, String> t) -> {
                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setDescription(t.getNewValue());
        });
        
        accountNameCol.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
        accountNameCol.setCellFactory(TextFieldTableCell.<Account>forTableColumn());
        accountNameCol.setOnEditCommit(
            (CellEditEvent<Account, String> t) -> {
                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setAccountName(t.getNewValue());
        });
        
        passwordCol.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.<Account>forTableColumn());
        passwordCol.setOnEditCommit(
            (CellEditEvent<Account, String> t) -> {
                ((Account) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setPassword(t.getNewValue());
        });
        
        removeButton.setOnAction((ActionEvent e) -> removeSelectedRow());
        addRowButton.setOnAction((ActionEvent e) -> addNewEmptyRow());
        saveButton.setOnAction((ActionEvent e) -> saveData());
        
    }
    
    /**
     * Constructor that initializes TableView data with observableArrayList.
     * Creates new Accounts object containing list of personal user accounts.
     * Path of users accounts is placed in BaseController.
     */
    public MainPanelController(){
        accounts = new Accounts(BaseController.getCurrentUser().getFilepath());
        data = FXCollections.observableArrayList();
    }    
    
    /**
     * Method saving data changed during user operation.
     * It's action called when user clicks on button.
     */
    public void addNewEmptyRow(){
        Account emptyRow = new Account("","","","");
        if(!accounts.getAccountList().contains(emptyRow)){
            accounts.addAccount(emptyRow);
            data.add(emptyRow);
        }
    }
    
    /**
     * Method saving data changed during user operation.
     * It's action called when user clicks on button.
     */
    public void removeSelectedRow(){
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
            table.getItems().remove(selectedIndex);
    }
    
    /**
     * Method saving data changed during user operation.
     * It's action called when user clicks on button.
     */
    public void saveData(){
        var tmpList = data;
        for(var acc : tmpList){
            if(acc.equals(new Account("", "", "", "")))
               tmpList.remove(acc);
        }
        accounts.setAccountList(tmpList);
        accounts.saveJSONFile(BaseController.getCurrentUser().getFilepath());
        MessageBox.showInformation("Sukces", "Zapisano dane");
    }
    
}

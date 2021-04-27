package pl.lipiec.Controllers;

import javax.swing.JOptionPane;
import pl.lipiec.Model.Account;
import pl.lipiec.Model.Accounts;
import pl.lipiec.Model.TableModel;

/**
 * MainPanel Controller class.
 * It provides main actions between model and view.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class MainPanelController {
    
    /**
     * Model that contains Account list and methods that allow list manipulation.
     */
    private Accounts accounts;
    /**
     * Model of the table used in view.
     */
    private TableModel tableModel;
    
    /**
     * Non-argument constructor.
     * Creatres new MainPanelController object and initializes new Accounts object.
     */
    public MainPanelController(){
        this.accounts = new Accounts(BaseController.getCurrentUser().getFilepath());
    }
    
    /**
     * Creatres new MainPanelController object and initializes new Accounts object.
     * Additionally sets TableModel object in class with object passed as argument.
     * @param tModel new model of table represented by TableModel object
     */
    public MainPanelController(TableModel tModel){
        this.accounts = new Accounts(BaseController.getCurrentUser().getFilepath());
        this.tableModel = tModel;
    }
    
    /**
     * Accounts object getter.
     * Accounts is model that holds list of accounds (Account objects).
     * @return currrent Accounts object.
     */
    public Accounts getAccounts() {
        return accounts;
    }
    /**
     * Sets current Accounts model with passed argument.
     * @param accountsModel object holding a new or updated Accounts model
     */
    public void setAccounts(Accounts accountsModel) {
        this.accounts = accountsModel;
    }
    /**
     * Current TableModel getter.
     * @return TableModel object
     */
    public TableModel getTableModel() {
        return tableModel;
    }
    /**
     * Current TableModel setter.
     * @param tableModel TableModel object
     */
    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    /**
     * Method that reacts to event called when user want to add a new row in table.
     * It adds a new blank record to the table and prevents adding more than 1 blank record.
     * @param model TableModel object used to manipulate list of accounts in used model.
     */
    public void addNewRow(TableModel model){
        Account emptyRow = new Account("","","","");
        tableModel.setData(accounts);
        if(!accounts.getAccountList().contains(emptyRow)){
            tableModel.getData().addAccount(emptyRow);
            tableModel.fireTableRowsInserted(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
        }
    }
    /**
     * Method which removes row selected by user.
     * Reacts to event called when user want to delete row after selecting it in the table.
     * It removes row from the list of current Accounts model.
     * @param rowIndex int pointing at selected row
     */
    public void deleteSelectedRow(int rowIndex){
        accounts.getAccountList().remove(rowIndex);
        tableModel.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    /**
     * Method called when data needs to be saved.
     * Reacts to events in view.
     * @param model TableModel object
     */
    public void saveData(TableModel model){
        var tmpList = model.getData().getAccountList();
        for(var acc : tmpList){
            if(acc.equals(new Account("", "", "", "")))
               model.getData().getAccountList().remove(acc);
        }
        model.setData(accounts);
        accounts.saveJSONFile(BaseController.getCurrentUser().getFilepath());
        
        JOptionPane.showMessageDialog(null, "Zapisano", "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Updates table data.
     * @param data Accounts object representing new data
     */
    public void updateData(Accounts data){
        this.accounts = data;
        tableModel.setData(accounts);
    }
}

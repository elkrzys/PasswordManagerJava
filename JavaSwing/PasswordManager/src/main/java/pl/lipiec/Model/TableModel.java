package pl.lipiec.Model;

import javax.swing.table.AbstractTableModel;

/**
 * Custom tanble model.
 * Extends AbstractTableModel and implements needed methods.
 * This model has 4 columns "Platforma", "Opis", "Nazwa konta" and "Hasło".
 * Cells are editable and data is stored in Accounts model which contains a list of Account objects.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public class TableModel extends AbstractTableModel {

    /**
     * Table data source.
     */
    private Accounts data;
    
    /**\
     * Constructor that sets data source of TableModel.
     * @param accounts data source
     */
    public TableModel(Accounts accounts) {
        this.data = accounts;
    }
    
    /**
     * Gets the table data source.
     * @return Accounts object.
     */
    public Accounts getData() {
        return data;
    }
    
    /**
     * Sets the data source of table.
     * @param data Accounts type object
     */
    public void setData(Accounts data) {
        this.data = data;
    }
    
    /**
     * Gets count of rows of current data list.
     * @return int representing amount of rows
     */
    @Override
    public int getRowCount() {
        //return accounts.size();
        return data.size();
    }
    /**
     * Returns count of table columns.
     * Change method body if more or less columns are needed.
     * @return 4, number of columns
     */
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    /**
     * Returns value at given cell.
     * @param rowIndex current row position
     * @param columnIndex current column position
     * @return value in the cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return data.get(rowIndex).getPlatformName();
            case 1: return data.get(rowIndex).getDescription();
            case 2: return data.get(rowIndex).getAccountName();
            case 3: return data.get(rowIndex).getPassword();
            default: return "";
        }
    }
    
    /**
     * Sets value in given cell.
     * Needed when table cells are editable.
     * @param aValue value that will be set, convert to String
     * @param rowIndex current editing row
     * @param columnIndex current editing column
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Account row = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                row.setPlatformName((String) aValue);
                break;
            case 1:
                row.setDescription((String) aValue);
                break;
            case 2:
                row.setAccountName((String) aValue);
                break;
            case 3:
                row.setPassword((String) aValue);
                break;
            default:
                break;
        }
    }
    
    /**
     * Return table headers.
     * @param columnIndex current column index.
     * @return name of the column
     */
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Platforma";
            case 1: return "Opis";
            case 2: return "Nazwa konta";
            case 3: return "Hasło";
            default: return "";
        }
    }
    
    /**
     * Sets the table cell as editable.
     * User can input and modify data in the cell.
     * @param rowIndex current editing row index
     * @param columnIndex current editing culmn index
     * @return true when editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
    
    
}

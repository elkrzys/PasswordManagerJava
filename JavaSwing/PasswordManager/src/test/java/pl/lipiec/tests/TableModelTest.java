package pl.lipiec.tests;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.lipiec.Model.Account;
import pl.lipiec.Model.Accounts;
import pl.lipiec.Model.TableModel;

/**
 * TableModel tests.
 * Contains of getting values of column or single cell from the table.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class TableModelTest {
    
    public TableModelTest() {
    }

    static TableModel model;
    
    /**
     * Initialization of TableModel for testing.
     */
    @BeforeAll
    public static void initAll() {
        Accounts accounts = new Accounts();
        accounts.addAccount(new Account("platform", "descr", "account", "password"));
        accounts.addAccount(new Account("another", "another", "another", "another"));
        accounts.addAccount(new Account("newone", "newone", "newone", "newone"));
        accounts.addAccount(new Account("nextone", "newone", "nextone", "nextone"));
        model = new TableModel(accounts);
    }
    
    /**
     * Testing returned values from table model.
     * Basically checks if returned value isn't equal to empty string for correct data.
     * Data set is returned from stream as arguments(int, int).
     * @param param1 index of row
     * @param param2 index of column
     */
    @ParameterizedTest
    @MethodSource("IndexProvider")
    public void testGetValueAt(int param1, int param2){
        if(param2 >= 0 && param2 <4)
            assertNotEquals("",model.getValueAt(param1, param2));
        else
            assertEquals("",model.getValueAt(param1, param2));
    }
    static Stream<Arguments> IndexProvider() {
        return Stream.of(
        // ok
        arguments(0, 0), arguments(1, 0), arguments(2, 1), arguments(3, 1),
        arguments(0, 2), arguments(1, 2), arguments(2, 3), arguments(3, 3),
        // wrong
        arguments(1, 5), arguments(3, 6), arguments(2, 7), arguments(2, -1));
    } 
    
    /**
     * Tests if column names are correct.
     * Tests if incorrect values of columnIndex are correctly handled.
     * @param param columnIndex
     */
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 2, 3, 4, 5}) 
    public void testGetValueAt(int param){
        String columnName = model.getColumnName(param);
        switch(param){
            case 0: assertEquals("Platforma", columnName);
            break;
            case 1: assertEquals("Opis", columnName);
            break;
            case 2: assertEquals("Nazwa konta", columnName);
            break;
            case 3: assertEquals("Hasło", columnName);
            break;
            default: assertEquals("", columnName);
        }
    }

}

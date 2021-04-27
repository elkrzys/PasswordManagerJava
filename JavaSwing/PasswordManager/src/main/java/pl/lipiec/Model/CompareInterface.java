package pl.lipiec.Model;

/**
 * Interface of lambda function used to compare two Account objects.
 * May be implemented in different ways of comparison.
 * @author Krzysztof Lipiec
 * @version 1.1
 */
public interface CompareInterface {
    /**
     * Compare method interface.
     * Gets two parameters of Account object.
     * @param a first Account to compare
     * @param b second Account to compare
     * @return implemented comparison boolean value
     */
        boolean cmp(Account a, Account b);
}

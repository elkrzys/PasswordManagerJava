package pl.lipiec.Exceptions;

/**
 * Exception thrown when password confirmation is not valid. 
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class InvalidPasswordConfirmationEx extends Exception{
    /**
     * Exception of invalid password confirmation.
     * @param errorMessage message
     */
    public InvalidPasswordConfirmationEx(String errorMessage){
        super(errorMessage);
    }
}

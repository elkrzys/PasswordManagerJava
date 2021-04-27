package pl.lipiec.Exceptions;

/**
 * Class containing Exception called when registration fails.
 * @author Krzysztof Lipiec
 * @version 1.0
 */
public class RegistrationFailedEx extends Exception{
    /**
     * Exception of failed user registration.
     * @param errorMessage message
     */
    public RegistrationFailedEx(String errorMessage){
        super(errorMessage);
    }
}

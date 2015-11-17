package exceptions;

/**
 * This Exception is thrown when there is a problem with a letter delivery
 */
public class LetterDeliveryException extends Exception {

	/**
	 * Serial version UID for serialization
	 */
	private static final long serialVersionUID = 5657351620013674987L;

	/**
	 * Constructor with message
	 * @param message The message to show when the exception is thrown
	 */
	public LetterDeliveryException(String message) {
		super(message);
	}
	

}

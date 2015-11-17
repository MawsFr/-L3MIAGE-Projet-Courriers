package model.content.letter;

import model.city.Inhabitant;

/**
 * This class represents an Acknowledgement of receipt
 */
public class AcknowledgementOfReceipt extends SimpleLetter {

	/**
	 * Constructor with the sender, the receiver and the content of this Acknowledgement of receipt 
	 * @param sender The sender of this Acknowledgement of receipt
	 * @param receiver The receiver if this Acknowledgement of receipt
	 * @param content The content of this Acknowledgement of receipt
	 */
	public AcknowledgementOfReceipt(Inhabitant sender, Inhabitant receiver, String content) {
		super(sender, receiver, content);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.SimpleLetter#toString()
	 */
	@Override
	public String toString() {
		return "an aknowledgment of receipt which is " + super.toString();
	}

	
	
}

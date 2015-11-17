package model.content.specialletter;

import exceptions.LetterDeliveryException;
import model.content.letter.AcknowledgementOfReceipt;
import model.content.letter.Letter;

/**
 * This class represents a Registered letter which is replied by an Acknowledgement of receipt when received by the receiver
 * @param <L> The type of the content contained
 * 
 * @see SpecialLetter
 * @see Letter
 */
public class RegisteredLetter<L extends Letter<?>> extends SpecialLetter<L>{

	/**
	 * Constructor with the content of this registered letter
	 * @param content The content of this registered letter
	 */
	public RegisteredLetter(L content) {
		super(content);
	}

	/* (non-Javadoc)
	 * @see model.content.specialletter.SpecialLetter#doAction()
	 */
	@Override
	public void doAction() throws LetterDeliveryException{
		super.doAction();
		AcknowledgementOfReceipt acknoledgementOfReceipt = new AcknowledgementOfReceipt(receiver, sender, "aknowledgment of receipt for " + this);
		
		if(acknoledgementOfReceipt.isAffordableBy(receiver)) {
			receiver.sendLetter(acknoledgementOfReceipt);
		}
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#getCost()
	 */
	public double getCost() {
		return content.getCost() + 15;
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#toString()
	 */
	@Override
	public String toString() {
		return "a registered " + super.toString();
	}
}

package model.content.specialletter;

import exceptions.LetterDeliveryException;
import model.content.letter.AcknowledgementOfReceipt;
import model.content.letter.Letter;

public class RegisteredLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public RegisteredLetter(L content) {
		super(content);
	}

	/* (non-Javadoc)
	 * @see model.content.specialletter.SpecialLetter#doAction()
	 */
	@Override
	public void doAction() throws LetterDeliveryException{
		super.doAction();
		//TODO : Peut etre vérifier s'il a assez d'argent sinon ca va déclencher une LetterDeliveryException !
		receiver.sendLetter(new AcknowledgementOfReceipt(receiver, sender, "aknowledgment of receipt for " + this));
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

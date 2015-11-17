package model.content.specialletter;

import exceptions.LetterDeliveryException;
import model.content.letter.Letter;

/**
 * This class represents a special letter which can contain all type of content (Text, Money, Letters)
 * @param <L> The type of the content
 * 
 * @see RegisteredLetter
 * @see UrgentLetter
 */
public abstract class SpecialLetter<L extends Letter<?>> extends Letter<L> {

	/**
	 * Constructor with the content
	 * @param content The content to carry
	 */
	public SpecialLetter(L content) {
		super(content.getSender(), content.getReceiver(), content);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#doAction()
	 */
	@Override
	public void doAction() throws LetterDeliveryException {
		content.doAction();
	}
	

}

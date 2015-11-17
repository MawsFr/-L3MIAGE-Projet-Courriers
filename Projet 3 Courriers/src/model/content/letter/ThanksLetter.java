package model.content.letter;

import model.city.Inhabitant;

public class ThanksLetter extends SimpleLetter {

	/**
	 * Constructor with the sender, the receiver and the content of this Thanks letter
	 * @param sender The sender of this thanks letter 
	 * @param receiver The receiver of this thanks letter 
	 * @param content The content of this thanks letter 
	 */	
	public ThanksLetter(Inhabitant sender, Inhabitant receiver, String content) {
		super(sender, receiver, content);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.SimpleLetter#toString()
	 */
	@Override
	public String toString() {
		return "a thanks letter which is " + super.toString();
	}
	
	

}

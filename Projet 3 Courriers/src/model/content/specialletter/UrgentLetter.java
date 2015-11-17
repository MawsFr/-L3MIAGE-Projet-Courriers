package model.content.specialletter;

import model.content.letter.Letter;

/**
 * This class represents an Urgent letter which is received the same day it has been sent (here we just doubled the cost) 
 * @param <L> The type of content contained
 * @see SpecialLetter
 * @see Letter
 */
public class UrgentLetter<L extends Letter<?>> extends SpecialLetter<L>{

	/**
	 * Constructor with the content of this urgent letter
	 * @param content The content of this urgent letter
	 */
	public UrgentLetter(L content) {
		super(content);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#getCost()
	 */
	@Override
	public double getCost() {
		return content.getCost() * 2;
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#toString()
	 */
	@Override
	public String toString() {
		return "an urgent " + super.toString();
	}

}

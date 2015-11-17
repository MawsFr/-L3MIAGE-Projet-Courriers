package model.content.specialletter;

import model.content.letter.Letter;

public class UrgentLetter<L extends Letter<?>> extends SpecialLetter<L>{

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

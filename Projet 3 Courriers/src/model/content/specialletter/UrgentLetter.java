package model.content.specialletter;

import model.content.letter.Letter;

public class UrgentLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public UrgentLetter(L content) {
		super(content);
	}
	
	@Override
	public double getCost() {
		return content.getCost() * 2;
	}
	
	@Override
	public String toString() {
		return "an urgent " + super.toString();
	}

}

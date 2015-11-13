package model.content.specialletter;

import model.city.Inhabitant;
import model.content.letter.Letter;

public class UrgentLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public UrgentLetter(Inhabitant sender, Inhabitant receiver, L content) {
		super(sender, receiver, content);
	}
	
	@Override
	public void doAction() {
		super.doAction();
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

package model.content.specialletter;

import model.Inhabitant;
import model.content.letter.Letter;

public class UrgentLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public UrgentLetter(Inhabitant sender, Inhabitant receiver, L content, double cost) {
		super(sender, receiver, content, cost);
	}
	
	@Override
	public void doAction() {
		super.doAction();
	}
	
	@Override
	public double getCost() {
		return super.getCost()*2;
	}

}

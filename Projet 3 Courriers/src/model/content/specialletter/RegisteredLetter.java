package model.content.specialletter;

import model.Inhabitant;
import model.content.letter.Letter;

public class RegisteredLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public RegisteredLetter(Inhabitant sender, Inhabitant receiver, L content, int cost) {
		super(sender, receiver, content, cost);
	}

	@Override
	public void doAction(){
		
	}
	
	@Override
	public int getCost(){
		return cost;
	}
}

package model.content.specialletter;

import model.Inhabitant;
import model.content.letter.Letter;

public class RegisteredLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public RegisteredLetter(Inhabitant sender, Inhabitant receiver, L content) {
		super(sender, receiver, content);
	}

	@Override
	public void doAction(){
		super.doAction();
		//TODO : Renvoi d'un accusé de reception
		
	}
	
	public double getCost() {
		return content.getCost() + 15;
	}
}

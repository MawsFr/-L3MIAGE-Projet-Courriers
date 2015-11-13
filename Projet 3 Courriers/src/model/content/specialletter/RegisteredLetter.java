package model.content.specialletter;

import model.Inhabitant;
import model.content.letter.Letter;
import model.content.letter.SimpleLetter;

public class RegisteredLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public RegisteredLetter(Inhabitant sender, Inhabitant receiver, L content) {
		super(sender, receiver, content);
	}

	@Override
	public void doAction(){
		super.doAction();
		//TODO : Renvoi d'un accusé de reception
		receiver.sendLetter(new SimpleLetter(receiver, sender, "Acknowledgment of receipt"));
		
	}
	
	public double getCost() {
		return content.getCost() + 15;
	}
}

package model.content.specialletter;

import model.content.letter.AknowledgmentOfReceiptLetter;
import model.content.letter.Letter;

public class RegisteredLetter<L extends Letter<?>> extends SpecialLetter<L>{

	public RegisteredLetter(L content) {
		super(content);
	}

	@Override
	public void doAction(){
		super.doAction();
		receiver.sendLetter(new AknowledgmentOfReceiptLetter(receiver, sender, "aknowledgment of receipt for " + this));
	}
	
	public double getCost() {
		return content.getCost() + 15;
	}
	
	@Override
	public String toString() {
		return "a registered " + super.toString();
	}
}

package model.content.letter;

import model.Inhabitant;

public class AknowledgmentOfReceiptLetter extends SimpleLetter {

	public AknowledgmentOfReceiptLetter(Inhabitant sender, Inhabitant receiver, String content) {
		super(sender, receiver, content);
	}
	
	@Override
	public String toString() {
		return "an aknowledgment of receipt which is " + super.toString();
	}

	
	
}

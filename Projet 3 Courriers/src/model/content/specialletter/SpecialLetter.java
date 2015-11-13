package model.content.specialletter;

import model.city.Inhabitant;
import model.content.letter.Letter;

public abstract class SpecialLetter<L extends Letter<?>> extends Letter<L> {

	public SpecialLetter(Inhabitant sender, Inhabitant receiver, L content) {
		super(sender, receiver, content);
	}
	
	@Override
	public void doAction() {
		content.doAction();
	}
	

}

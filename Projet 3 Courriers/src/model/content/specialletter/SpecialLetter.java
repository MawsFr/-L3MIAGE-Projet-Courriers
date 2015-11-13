package model.content.specialletter;

import model.Inhabitant;
import model.content.letter.Letter;

public abstract class SpecialLetter<L extends Letter<?>> extends Letter<L> {

	public SpecialLetter(Inhabitant sender, Inhabitant receiver, L content, double cost) {
		super(sender, receiver, content, cost);
	}
	
	

}

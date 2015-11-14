package model.content.specialletter;

import model.content.letter.Letter;

public abstract class SpecialLetter<L extends Letter<?>> extends Letter<L> {

	public SpecialLetter(L content) {
		super(content.getSender(), content.getReceiver(), content);
	}
	
	@Override
	public void doAction() {
		content.doAction();
	}
	

}

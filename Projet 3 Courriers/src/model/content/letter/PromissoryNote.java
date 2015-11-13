package model.content.letter;

import model.Inhabitant;
import model.content.Money;

public class PromissoryNote extends Letter<Money> {
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, int cost, int amount) {
		super(sender, receiver, new Money(amount), cost);
	}
	
	@Override
	public void doAction() {
		super.doAction();
		
	}

}

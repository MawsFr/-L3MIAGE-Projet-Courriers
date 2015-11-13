package model.content.letter;

import model.Inhabitant;
import model.content.Money;

public class PromissoryNote extends Letter<Money> {
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, double cost, int amount) {
		super(sender, receiver, new Money(amount), cost);
	}
	
	@Override
	public void doAction() {
		this.sender.debit(this.content.getAmount());
		this.receiver.credit(this.content.getAmount());
		this.receiver.sentdLetter(new SimpleLetter(receiver, sender, "Merci"));
	}
	
	@Override
	public double getCost() {
		return super.getCost()+(0.01*super.getCost());
	}

}

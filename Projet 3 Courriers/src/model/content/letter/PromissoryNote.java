package model.content.letter;

import model.Inhabitant;
import model.content.Money;

public class PromissoryNote extends Letter<Money> {
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, double amount) {
		super(sender, receiver, new Money(amount));
	}
	
	@Override
	public void doAction() {
		this.sender.debit(this.content.getAmount());
		this.receiver.credit(this.content.getAmount());
		this.receiver.sendLetter(new SimpleLetter(receiver, sender, "Thanks"));
	}
	
	@Override
	public double getCost() {
		return 1 + (0.01 * content.getAmount());
	}
	
}

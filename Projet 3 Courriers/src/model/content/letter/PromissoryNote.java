package model.content.letter;

import model.city.Inhabitant;
import model.content.Money;
import model.content.Text;

public class PromissoryNote extends Letter<Money> {
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, double amount) {
		super(sender, receiver, new Money(amount));
	}
	
	@Override
	public void doAction() {
		this.sender.debit(this.content.getAmount());
		this.receiver.credit(this.content.getAmount());
		
		Letter<Text> thanksLetter = new ThanksLetter(receiver, sender, "thanks for " + this);
		this.receiver.sendLetter(thanksLetter);
	}
	
	@Override
	public double getCost() {
		return 1 + (0.01 * content.getAmount());
	}
	
	@Override
	public String toString() {
		return "a promissory note " + super.toString();
	}
	
}

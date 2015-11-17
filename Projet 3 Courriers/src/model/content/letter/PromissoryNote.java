package model.content.letter;

import exceptions.LetterDeliveryException;
import model.city.Inhabitant;
import model.content.Money;
import model.content.Text;

public class PromissoryNote extends Letter<Money> {
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, double amount) {
		super(sender, receiver, new Money(amount));
	}
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, Money money) {
		super(sender, receiver, money);
	}
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver) {
		this(sender, receiver, 1);
	}
	
	@Override
	public void doAction() throws LetterDeliveryException {
		this.sender.debit(this.content.getAmount());
		this.receiver.credit(this.content.getAmount());
		
		Letter<Text> thanksLetter = new ThanksLetter(receiver, sender, "thanks for " + this);
		//TODO : Peut etre vérifier s'il a assez d'argent sinon ca va déclencher une LetterDeliveryException !
		this.receiver.sendLetter(thanksLetter);
	}
	
	@Override
	public boolean isAffordableBy(Inhabitant inhabitant) {
		return inhabitant.getBankAccount() > (getCost() + content.getAmount());
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

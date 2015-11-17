package model.content.letter;

import exceptions.LetterDeliveryException;
import model.city.Inhabitant;
import model.content.Money;
import model.content.Text;

/**
 * This class represents a Promissory note which will containt an amount of money to send to an inhabitant
 * 
 * @see Letter
 * @see Money
 */
public class PromissoryNote extends Letter<Money> {
	
	/**
	 * Constructor with the sender, the receiver and the amount of money the letter will carry
	 * @param sender The sender of the money
	 * @param receiver The receiver of the money
	 * @param amount The amount to send
	 */
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, double amount) {
		super(sender, receiver, new Money(amount));
	}
	
	/**
	 * Constructor with the sender, the receiver and the  the letter will carry
	 * @param sender The sender of the money
	 * @param receiver The receiver of the money
	 * @param amount The money to send
	 */
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, Money money) {
		super(sender, receiver, money);
	}
	
	/**
	 * Constructor with the sender, the receiver and a default amount of money (here 1)
	 * @param sender The sender of the money
	 * @param receiver The receiver of the money
	 */
	public PromissoryNote(Inhabitant sender, Inhabitant receiver) {
		this(sender, receiver, 1);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#doAction()
	 */
	@Override
	public void doAction() throws LetterDeliveryException {
		this.sender.debit(this.content.getAmount());
		this.receiver.credit(this.content.getAmount());
		
		Letter<Text> thanksLetter = new ThanksLetter(receiver, sender, "thanks for " + this);
		//TODO : Peut etre vérifier s'il a assez d'argent sinon ca va déclencher une LetterDeliveryException !
		this.receiver.sendLetter(thanksLetter);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#isAffordableBy(model.city.Inhabitant)
	 */
	@Override
	public boolean isAffordableBy(Inhabitant inhabitant) {
		return inhabitant.getBankAccount() > (getCost() + content.getAmount());
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#getCost()
	 */
	@Override
	public double getCost() {
		return 1 + (0.01 * content.getAmount());
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#toString()
	 */
	@Override
	public String toString() {
		return "a promissory note " + super.toString();
	}
	
}

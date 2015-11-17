package model.content.letter;

import exceptions.LetterDeliveryException;
import model.city.Inhabitant;
import model.content.Content;
import model.content.specialletter.RegisteredLetter;
import model.content.specialletter.SpecialLetter;
import model.content.specialletter.UrgentLetter;
import model.observable.ExtendedObservable;

/**
 * This class represents a Letter
 * @param <C> The content type this letter will contain
 * 
 * @see SpecialLetter
 * @see SimpleLetter
 * @see PromissoryNote
 * @see UrgentLetter
 * @see RegisteredLetter
 * @see ThanksLetter
 * @see AcknowledgementOfReceipt
 */
public abstract class Letter<C extends Content> extends ExtendedObservable implements Content {
	
	/**
	 * The sender of the letter
	 */
	protected Inhabitant sender;
	
	/**
	 * The receiver of the letter 
	 */
	protected Inhabitant receiver;
	
	/**
	 * The content of the letter 
	 */
	protected C content;
	
	/**
	 * Constructor with the sender and the receiver of this letter
	 * @param sender The sender of this letter
	 * @param receiver The receiver of this letter 
	 */
	public Letter(Inhabitant sender, Inhabitant receiver) {
		this(sender, receiver, null);
	}
	
	/**
	 * Constructor with the sender, the receiver and the content of this letter
	 * @param sender The sender of this letter
	 * @param receiver The receiver of this letter 
	 * @param content The content of this letter 
	 */	
	public Letter(Inhabitant sender, Inhabitant receiver, C content) {
		if(sender == null) {
			throw new NullPointerException("You must specify a non null sender parameter");
		}
		
		if(receiver == null) {
			throw new NullPointerException("You must specify a non null receiver parameter");
		}
		
		if(content == null) {
			throw new NullPointerException("You must specify a non null content");
		}
		
		if(sender == receiver) {
			throw new IllegalArgumentException("The sender cannot be equal to the receiver");
		}
		
		//TODO : Peut etre verifier si content est null en fait ..
		//TODO : Vérifier les parametres
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	/**
	 * Execute actions when the letter is received
	 * @throws LetterDeliveryException 
	 */
	public abstract void doAction() throws LetterDeliveryException;
	
	/**
	 * @return The cost of this letter
	 */
	public abstract double getCost();
	
	/**
	 * Returns if the letter is affordable for the inhabitant
	 * @param inhabitant The inhabitant for whom we check if the letter is affordable
	 * @return True if yes, else false
	 */
	public boolean isAffordableBy(Inhabitant inhabitant) {
		if(inhabitant == null) {
			throw new NullPointerException("You must specify a non null inhabitant parameter");
		}
		
		return inhabitant.getBankAccount() >= this.getCost();
	}

	/**
	 * @return The sender of this letter
	 */
	public Inhabitant getSender() {
		return sender;
	}
	
	/**
	 * @return the receiver if this letter
	 */
	public Inhabitant getReceiver() {
		return receiver;
	}
	
	/**
	 * @return The content of the letter
	 */
	public C getContent() {
		return content;
	}
	
//	/**
//	 * Sets the content of this letter
//	 * @param content The content to set (can be null)
//	 */
//	public void setContent(C content) {
//		this.content = content;
//	}
		
	public void notify(String message) {
		setChanged();
		notifyObservers(message);
	}

	@Override
	public String toString() {
		return "letter" + " whose content is " + content;
	}
}

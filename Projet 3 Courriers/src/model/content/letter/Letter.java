package model.content.letter;

import model.city.Inhabitant;
import model.content.Content;
import model.observable.ExtendedObservable;

/**
 * This class represents a Letter
 * @param <C>
 */
public abstract class Letter<C extends Content> extends ExtendedObservable implements Content {
	
	protected Inhabitant sender;
	protected Inhabitant receiver;
	protected C content;
	
	public Letter(Inhabitant sender, Inhabitant receiver) {
		this(sender, receiver, null);
	}
	
	public Letter(Inhabitant sender, Inhabitant receiver, C content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public abstract void doAction();
	public abstract double getCost();
	
	public boolean isAfordableBy(Inhabitant inhabitant) {
		return inhabitant.getBankAccount() >= this.getCost();
	}

	public Inhabitant getSender() {
		return sender;
	}
	
	public Inhabitant getReceiver() {
		return receiver;
	}
	
	public C getContent() {
		return content;
	}
	
	public void setContent(C content) {
		this.content = content;
	}
		
	public void notify(String message) {
		setChanged();
		notifyObservers(message);
	}

	@Override
	public String toString() {
		return "letter" + " whose content is " + content;
	}
}

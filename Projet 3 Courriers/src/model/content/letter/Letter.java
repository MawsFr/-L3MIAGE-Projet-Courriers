package model.content.letter;

import model.Inhabitant;
import model.content.Content;

public abstract class Letter<C extends Content> implements Content {
	
	protected Inhabitant sender;
	protected Inhabitant receiver;
	protected C content;
	
	
	public Letter(Inhabitant sender, Inhabitant receiver, C content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public abstract void doAction();
	
	public abstract double getCost();

	@Override
	public String getContent() {
		return content.getContent();
	}
	
	public Inhabitant getSender() {
		return sender;
	}
	
	public Inhabitant getReceiver() {
		return receiver;
	}
		

}

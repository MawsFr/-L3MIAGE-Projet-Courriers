package model.content.letter;

import model.Inhabitant;
import model.content.Content;

public abstract class Letter<C extends Content> implements Content {
	
	protected Inhabitant sender;
	protected Inhabitant receiver;
	protected Content content;
	protected double cost;
	
	
	public Letter(Inhabitant sender, Inhabitant receiver, Content content, double cost) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.cost = cost;
	}

	public void doAction() {
		
	}
	
	public double getCost() {
		return cost;
	}

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

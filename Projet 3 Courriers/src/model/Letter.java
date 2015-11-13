package model;

public class Letter<C extends Content> implements Content {
	
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
		

}

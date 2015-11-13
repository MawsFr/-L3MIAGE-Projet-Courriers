package model;

public class PromissoryNote extends Letter<Money> {
	
	public PromissoryNote(Inhabitant sender, Inhabitant receiver, double cost,int amount) {
		super(sender, receiver,new Money(amount), cost);
	}
	
	@Override
	public void doAction() {
		super.doAction();
		
	}

}

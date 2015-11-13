package model.content;

public class Money implements Content{

	protected int amount;
	
	public Money(int amount){
		this.amount = amount;
	}
	
	@Override
	public String getContent() {
		return "" + this.amount;
	}
}

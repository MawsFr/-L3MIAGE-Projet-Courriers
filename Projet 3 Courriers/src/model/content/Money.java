package model.content;

public class Money implements Content{

	protected double amount;
	
	public Money(double amount){
		this.amount = amount;
	}
	
	@Override
	public String getContent() {
		return "" + this.amount;
	}
	
	public double getAmount() {
		return amount;
	}
}

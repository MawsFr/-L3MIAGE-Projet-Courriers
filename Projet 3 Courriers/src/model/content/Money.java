package model.content;

public class Money implements Content{

	protected double amount;
	
	public Money(double amount){
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return "a money content (" + amount + ")";
	}
}

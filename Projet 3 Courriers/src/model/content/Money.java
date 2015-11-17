package model.content;

public class Money implements Content{

	protected double amount;
	
	public Money(double amount){
		this.amount = amount;
	}
	
	/**
	 * @return The amount of money
	 */
	public double getAmount() {
		return amount;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "a money content (" + amount + ")";
	}
}

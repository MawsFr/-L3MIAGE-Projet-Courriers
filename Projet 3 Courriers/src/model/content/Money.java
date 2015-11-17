package model.content;

/**
 * This class represents a amount of money that can be sent by an inhabitant to another
 * 
 * @see Content
 */
public class Money implements Content{

	/**
	 * The money amount
	 */
	protected double amount;
	
	/**
	 * Constructor with an amount of money
	 * @param amount The money amount
	 */
	public Money(double amount){
		if(amount <= 0) {
			throw new IllegalArgumentException("You must specify a positive amount of money");
		}
		
		//TODO : positve not null or negative
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

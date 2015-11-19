package content;

import model.content.Money;

import org.junit.Test;

/**
 * This class tests the Money class 
 */
public class MoneyTest {

	/**
	 * Tests creating a Money object with negative amount
	 */
	@Test(expected=IllegalArgumentException.class)
	public void negativeAmountTest() {
		new Money(-1);
	}

	/**
	 * Tests creating a Money object with null amount
	 */
	@Test(expected=IllegalArgumentException.class)
	public void nullAmountTest() {
		new Money(0);
	}
}

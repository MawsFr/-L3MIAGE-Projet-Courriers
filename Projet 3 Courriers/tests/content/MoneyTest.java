package content;

import model.content.Money;

import org.junit.Test;

public class MoneyTest {

	@Test(expected=IllegalArgumentException.class)
	public void negativeAmountTest() {
		new Money(-1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void nullAmountTest() {
		new Money(0);
	}
}

package city;

import static org.junit.Assert.assertEquals;
import model.city.City;
import model.city.Inhabitant;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;

import org.junit.Before;
import org.junit.Test;

import content.mockclasses.MockInhabitant;
import exceptions.LetterDeliveryException;

public class InhabitantTest {
	
	/**
	 * A city attribute 
	 */
	protected City city;
	
	/**
	 * Another city attribute
	 */
	protected City city2;
	
	/**
	 * An inhabitant attribute
	 */
	protected MockInhabitant sender;
	
	/**
	 * An another inhabitant attribute
	 */
	protected MockInhabitant receiver;
	
	/**
	 * Sets some variable
	 */
	@Before
	public void setUp() {
		city = new City("City");
		city2 = new City("City 2");
		sender = new MockInhabitant(city);
		receiver = new MockInhabitant(city2);
		
		city.addInhabitant(sender);
		city2.addInhabitant(receiver);
		
	}
	
	/**
	 * Tests creating a inhabitant with a null name	
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void inhabitantWithNullNameTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant(null, city);
	}
	
	/**
	 * Tests creating a inhabitant with an empty name	
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void inhabitantWithEmptyNameTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant("", city);
	}
	
	/**
	 * Tests creating a inhabitant with a null city
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void inhabitantWithNullCityTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant("Maws", null);
	}
	
	/**
	 * Tests creating an inhabitant with a negative bank account amount
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void inhabitantWithNegativeBankAccountTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant("Maws", city, -1);
	}
	
	/**
	 * Tests creating an inhabitant with a null bank account amount
	 */
	@Test
	public void inhabitantWithNullBankAccountTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant("Maws", city, 0);
	}
	
	/**
	 * Tests crediting negative amount
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void creditNegativeAmountTest() {
		sender.credit(-1);
	}
	
	/**
	 * Tests crediting a null amount
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void creditNullAmountTest() {
		sender.credit(0);
	}
	
	/**
	 * Tests debiting a negative amount
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void debitNegativeAmountTest() {
		sender.debit(-1);
	}
	
	/**
	 * Tests debiting a null amount
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void debitNullAmountTest() {
		sender.debit(0);
	}
	
	/**
	 * Tests sending a null letter argument
	 * @throws LetterDeliveryException
	 */
	@Test(expected=NullPointerException.class)
	public void sendNullLetterTest() throws LetterDeliveryException {
		sender.sendLetter(null);;
	}
	
	
	
	
	
	
	
}

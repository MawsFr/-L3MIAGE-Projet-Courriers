package city;

import static org.junit.Assert.*;
import model.city.City;
import model.city.Inhabitant;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;

import org.junit.Before;
import org.junit.Test;

import content.mockclasses.MockInhabitant;
import exceptions.LetterDeliveryException;

public class InhabitantTest {
	
	protected City city;
	protected City city2;
	protected MockInhabitant sender;
	protected MockInhabitant receiver;
	
	@Before
	public void setUp() {
		city = new City("City");
		city2 = new City("City 2");
		sender = new MockInhabitant(city);
		receiver = new MockInhabitant(city2);
		
		city.addInhabitant(sender);
		city2.addInhabitant(receiver);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void inhabitantWithNullNameTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant(null, city);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void inhabitantWithEmptyNameTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant("", city);
	}
	
	@Test(expected=NullPointerException.class)
	public void inhabitantWithNullCityTest() {
		@SuppressWarnings("unused")
		Inhabitant inhabitant = new Inhabitant("Maws", null);
	}
	
//	@Test(expected=IllegalArgumentException.class)
//	public void inhabitantWithNegativeBankAccountTest() {
//		TODO : A voir si on autorise le découvert
//	}
	
	@Test(expected=IllegalArgumentException.class)
	public void creditNegativeAmountTest() {
		sender.credit(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void creditNullAmountTest() {
		sender.credit(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void debitNegativeAmountTest() {
		sender.debit(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void debitNullAmountTest() {
		sender.debit(0);
	}
	
	@Test(expected=NullPointerException.class)
	public void sendNullLetterTest() throws LetterDeliveryException {
		sender.sendLetter(null);;
	}
	
	@Test(expected=LetterDeliveryException.class)
	public void sendNotAffordableLetterTest() throws LetterDeliveryException {
		assertEquals(0, sender.getNumberOfLetterSent());
		assertEquals(5000.0d, sender.getBankAccount(), 0);
		PromissoryNote letter = new PromissoryNote(sender, receiver, 6000);
		sender.sendLetter(letter);
		
	}
	
	@Test(expected=LetterDeliveryException.class)
	public void sendNotOwnedLetterTest() throws LetterDeliveryException {
		assertEquals(0, sender.getNumberOfLetterSent());
		assertEquals(5000.0d, sender.getBankAccount(), 0);
		PromissoryNote letter = new PromissoryNote(receiver, sender, 1);
		sender.sendLetter(letter);
	}
	
	@Test(expected=NullPointerException.class)
	public void receiveNullLetterTest() throws LetterDeliveryException {
		receiver.receiveLetter(null);
	}
	
	@Test(expected=LetterDeliveryException.class)
	public void receiveNotDestinedLetterTest() throws LetterDeliveryException {
		SimpleLetter letter = new SimpleLetter(receiver, sender);
		receiver.receiveLetter(letter);
	}
	
	
	
	
	
}

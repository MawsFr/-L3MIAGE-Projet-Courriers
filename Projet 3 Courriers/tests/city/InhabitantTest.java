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
	
	/**
	 * Sends a letter a does assert on expected numbers of sent and received letters by both inhabitant 
	 * @param letter The letter to send
	 * @param expectedSenderSentLetters The expected number of sender's sent letters 
	 * @param expectedSenderReceivedLetters The expected number of sender's received letters
	 * @param expectedReceiverSentLetter The expected number of receiver's sent letters
	 * @param expectedReceiverReceivedLetter The expected number of receiver's received letters
	 * @param promissoryNoteAmount The amount of the promissory note if we passed a promissory letter
	 * @throws LetterDeliveryException If the letter is not afordable or null or sender doesn't own the letter
	 */
	protected void sendLetter(Letter<?> letter, int expectedSenderSentLetters, int expectedSenderReceivedLetters, int expectedReceiverSentLetter, int expectedReceiverReceivedLetter, double promissoryNoteAmount /*default zero if not a promissory note letter*/) throws LetterDeliveryException {
		double senderBankAccount = sender.getBankAccount();
		assertEquals(letter.getSender(), sender);
		assertEquals(letter.getReceiver(), receiver);
		assertEquals(0, sender.getNumberOfLetterSent());
		assertEquals(5000.0d, sender.getBankAccount(), 0);
		assertTrue(letter.isAffordableBy(sender));
		sender.sendLetter(letter);
		assertTrue(city.hasLettersToSend());
		assertEquals(1, city.getNbLettersInPostBox());
		city.distibuteLetters();
		assertFalse(city.hasLettersToSend());
		assertEquals(expectedSenderSentLetters, sender.getNumberOfLetterSent());
		assertEquals(expectedReceiverReceivedLetter, receiver.getNumberOfLetterReceived());
		assertEquals(senderBankAccount - letter.getCost() - promissoryNoteAmount, sender.getBankAccount(), 0);

		if(city.hasLettersToSend()) {
			city.distibuteLetters();
			assertFalse(city.hasLettersToSend());
			assertEquals(expectedReceiverSentLetter, receiver.getNumberOfLetterSent());
			assertEquals(expectedSenderReceivedLetters, sender.getNumberOfLetterReceived());
		}
	}
	
	@Test
	public void sendAfordableSimpleLetterTest() throws LetterDeliveryException {
		SimpleLetter simpleLetter = new SimpleLetter(sender, receiver, "Bonjour");
		sendLetter(simpleLetter, 1, 0, 0, 1, 0);
	}
	
	@Test
	public void sendAfordablePromissoryNoteTest() throws LetterDeliveryException {
		double receiverBankAccount = receiver.getBankAccount();
		PromissoryNote promissoryNote = new PromissoryNote(sender, receiver, 2000d);
		sendLetter(promissoryNote, 1, 1, 1, 1, promissoryNote.getContent().getAmount());
		assertEquals(receiverBankAccount + promissoryNote.getContent().getAmount() - 1 /* 1 is the cost of Thanks letter */, receiver.getBankAccount(), 0);
		assertEquals(21.0, promissoryNote.getCost(), 0);
	}
	
	//TODO : Les tests sur registered urgent etc ...
	
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

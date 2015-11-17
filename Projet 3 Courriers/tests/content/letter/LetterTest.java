package content.letter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.city.City;
import model.content.Content;
import model.content.letter.Letter;

import org.junit.Before;
import org.junit.Test;

import content.mockclasses.MockInhabitant;
import exceptions.LetterDeliveryException;


public abstract class LetterTest<C extends Content> {
	
	protected City city1, city2;
	protected MockInhabitant sender;
	protected MockInhabitant receiver;
	
	@Before
	public void setUp() {
		this.city1 = new City("City 1");
		this.city2 = new City("City 2");
		sender = new MockInhabitant(city1);
		receiver = new MockInhabitant(city2);
	}
	
	public abstract Letter<?> createLetter();
	public abstract Letter<?> createLetter(C content);
	
	@Test
	public void allLetterHasAPositiveCost() {
		Letter<?> letter = createLetter();
		assertTrue(letter.getCost() > 0);
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
		assertTrue(city1.hasLettersToSend());
		assertEquals(1, city1.getNbLettersInPostBox());
		city1.distibuteLetters();
		assertFalse(city1.hasLettersToSend());
		assertEquals(expectedSenderSentLetters, sender.getNumberOfLetterSent());
		assertEquals(expectedReceiverReceivedLetter, receiver.getNumberOfLetterReceived());
		assertEquals(senderBankAccount - letter.getCost() - promissoryNoteAmount, sender.getBankAccount(), 0);

		if(city2.hasLettersToSend()) {
			city2.distibuteLetters();
			assertFalse(city2.hasLettersToSend());
		}
		
		assertEquals(expectedReceiverSentLetter, receiver.getNumberOfLetterSent());
		assertEquals(expectedSenderReceivedLetters, sender.getNumberOfLetterReceived());

	}
	
	@Test(expected=NullPointerException.class)
	public void letterWithNullSenderTest() {
		sender = null;
		createLetter();
	}
	
	@Test(expected=NullPointerException.class)
	public void letterWithNullReceiverTest() {
		receiver = null;
		createLetter();
	}
	
	@Test(expected=NullPointerException.class)
	public void letterWithNullContentTest() {
		createLetter(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void letteWithSenderEqualsReceiverTest() {
		sender = receiver;
		createLetter();
	}
	
	@Test(expected=NullPointerException.class)
	public void nullInhabitantParameterInIsAffordableLetterTest() {
		createLetter().isAffordableBy(null);
	}
	

}

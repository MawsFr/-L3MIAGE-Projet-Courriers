package content.letter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.city.City;
import model.content.Content;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;

import org.junit.Before;
import org.junit.Test;

import content.mockclasses.MockInhabitant;
import exceptions.LetterDeliveryException;


public abstract class LetterTest<C extends Content> {
	
	/**
	 * Some city attributes
	 */
	protected City city1, city2;
	
	/**
	 * A sender of letters 
	 */
	protected MockInhabitant sender;
	
	/**
	 * A receiver of letters 
	 */
	protected MockInhabitant receiver;
	
	/**
	 * Sets some variables
	 */
	@Before
	public void setUp() {
		this.city1 = new City("City 1");
		this.city2 = new City("City 2");
		sender = new MockInhabitant(city1);
		receiver = new MockInhabitant(city2);
	}
	
	/**
	 * @return A letter with the type of the test class (if its a special letter it will contain a simpleletter) 
	 */
	public abstract Letter<?> createLetter();
	
	/**
	 * @param content the content of the letter
	 * @return A letter with the content passed in parameter
	 */
	public abstract Letter<?> createLetter(C content);
	
	/**
	 * Tests if a letter has a positive cost
	 */
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
	
	/**
	 * Tests ceratign a letter with a null sender 
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void letterWithNullSenderTest() {
		sender = null;
		createLetter();
	}
	
	/**
	 * Tests ceratign a letter with a null receiver
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void letterWithNullReceiverTest() {
		receiver = null;
		createLetter();
	}
	
	/**
	 * Tests ceratign a letter with a null content
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void letterWithNullContentTest() {
		createLetter(null);
	}
	
	/**
	 * Tests creating a letter with same person as sender and receiver
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void letteWithSenderEqualsReceiverTest() {
		sender = receiver;
		createLetter();
	}
	
	/**
	 * Tests testing if a letter is affordable by null inhabitant
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void nullInhabitantParameterInIsAffordableLetterTest() {
		createLetter().isAffordableBy(null);
	}
	
	/**
	 * Tests sending an unaffordable letter
	 * @throws LetterDeliveryException
	 */
	@Test(expected=LetterDeliveryException.class)
	public void sendUnaffordableLetterTest() throws LetterDeliveryException {
		assertEquals(0, sender.getNumberOfLetterSent());
		assertEquals(5000.0d, sender.getBankAccount(), 0);
		sender.debit(5000);
		Letter<?> letter = createLetter();
		sender.sendLetter(letter);
		
	}
	
	/**
	 * Tests sending a letter not owned by the potential sender
	 * @throws LetterDeliveryException
	 */
	@Test(expected=LetterDeliveryException.class)
	public void sendNotOwnedLetterTest() throws LetterDeliveryException {
		assertEquals(0, sender.getNumberOfLetterSent());
		assertEquals(5000.0d, sender.getBankAccount(), 0);
		PromissoryNote letter = new PromissoryNote(receiver, sender, 1);
		sender.sendLetter(letter);
	}
	
	/**
	 * Tests receiving a null letter
	 * @throws LetterDeliveryException
	 */
	@Test(expected=NullPointerException.class)
	public void receiveNullLetterTest() throws LetterDeliveryException {
		receiver.receiveLetter(null);
	}
	
	/**
	 * Tests receiving a letter which was destined to another receiver
	 * @throws LetterDeliveryException
	 */
	@Test(expected=LetterDeliveryException.class)
	public void receiveNotDestinedLetterTest() throws LetterDeliveryException {
		SimpleLetter letter = new SimpleLetter(receiver, sender);
		receiver.receiveLetter(letter);
	}

}

package content.specialletter;

import static org.junit.Assert.assertEquals;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;
import model.content.specialletter.RegisteredLetter;

import org.junit.Test;

import exceptions.LetterDeliveryException;

/**
 * This class tests a registered letter
 */
public class RegisteredLetterTest extends SpecialLetterTest<Letter<?>>{

	@Override
	public RegisteredLetter<Letter<?>> createLetter() {
		return createLetter(new SimpleLetter(sender, receiver));
	}

	@Override
	public RegisteredLetter<Letter<?>> createLetter(Letter<?> content) {
		return new RegisteredLetter<Letter<?>>(content);
	}

	/**
	 * Tests if teh receiver indeed send back an ackowledgement of receipt after receiving a registered letter
	 */
	@Test
	public void receiverSendsAcknowledgment() throws LetterDeliveryException {
		assertEquals(0, receiver.getNumberOfLetterSent());
		createLetter().doAction();
		assertEquals(1, receiver.getNumberOfLetterSent());
	}

	/**
	 * Tests sending an affordable registered simple letter
	 */
	@Test
	public void sendAffordableRegisteredSimpleLetterTest() throws LetterDeliveryException {
		RegisteredLetter<Letter<?>> registeredSimpleLetter = createLetter();
		assertEquals(16, registeredSimpleLetter.getCost(), 0);
		sendLetter(registeredSimpleLetter, 1, 1, 1, 1, 0);
	}
	
	/**
	 * Tests sending a registered promissory note letter
	 */
	@Test
	public void sendAffordableRegisteredPromissoryNoteTest() throws LetterDeliveryException {
		double receiverBankAccount = receiver.getBankAccount();
		PromissoryNote promissoryNote = new PromissoryNote(sender, receiver, 1000d);
		RegisteredLetter<Letter<?>> registeredPromissoryNote = createLetter(promissoryNote);
		assertEquals(11.0, promissoryNote.getCost(), 0);
		assertEquals(1 + 10 + 15, registeredPromissoryNote.getCost(), 0);
		sendLetter(registeredPromissoryNote, 1, 2, 2, 1, promissoryNote.getContent().getAmount());
		assertEquals(receiverBankAccount + promissoryNote.getContent().getAmount() - 2 /* 2 is the cost of Thanks and Registered letter */, receiver.getBankAccount(), 0);	
	}
	
	/**
	 * Tests sending an unaffordable acknoledgement of receipt after receiving a registered simple letter
	 * There are no exception because it's not the fault of the receiver if his account is not reloaded of money
	 */
	@Test
	public void sendUnaffordableAcknoledgementOfReceiptWithSimpleLetterTest() throws LetterDeliveryException {
		receiver.setBankAccount(0);
		RegisteredLetter<Letter<?>> registeredSimpleLetter = createLetter();
		assertEquals(16, registeredSimpleLetter.getCost(), 0);
		sendLetter(registeredSimpleLetter, 1, 0, 0, 1, 0);
	}
	
	/**
	 * Tests sending back an affordable thanks letter but unaffordable acknoledgement of receipt
	 */
	@Test
	public void sendAffordableThanksLetterButUnaffordableAcknoledgementOfReceiptTest() throws LetterDeliveryException {
		receiver.setBankAccount(-999d);
		PromissoryNote promissoryNote = new PromissoryNote(sender, receiver, 1000d);
		RegisteredLetter<Letter<?>> registeredPromissoryNote = createLetter(promissoryNote);
		assertEquals(1d + 10d + 15d, registeredPromissoryNote.getCost(), 0);
		sendLetter(registeredPromissoryNote, 1, 1, 1, 1, promissoryNote.getContent().getAmount());
		assertEquals(-999d + promissoryNote.getContent().getAmount() - 1 /* 1 is the cost of Thanks letter */, receiver.getBankAccount(), 0);
		assertEquals(11.0d, promissoryNote.getCost(), 0);
	}
	
	/**
	 * Tests sending back unaffordable thanks letter and acknoledgement of receipt
	 */
	@Test
	public void sendUnaffordableAcknoledgementOfReceiptAndThanksLetterTest() throws LetterDeliveryException {
		receiver.setBankAccount(-2000d);
		PromissoryNote promissoryNote = new PromissoryNote(sender, receiver, 1000d);
		RegisteredLetter<Letter<?>> registeredPromissoryNote = createLetter(promissoryNote);
		assertEquals(1d + 10d + 15d, registeredPromissoryNote.getCost(), 0);
		sendLetter(registeredPromissoryNote, 1, 0, 0, 1, promissoryNote.getContent().getAmount());
		assertEquals(-2000d + promissoryNote.getContent().getAmount() /* There are no cost for a thanks letter or acknoledgement of receipt because the receiver has not enough money*/, receiver.getBankAccount(), 0);
		assertEquals(11.0d, promissoryNote.getCost(), 0);
	}
	
}
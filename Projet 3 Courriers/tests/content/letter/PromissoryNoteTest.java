package content.letter;

import static org.junit.Assert.assertEquals;
import model.content.Money;
import model.content.letter.PromissoryNote;

import org.junit.Test;

import exceptions.LetterDeliveryException;


/**
 * Thsi class tests the PromissoryNote class 
 *
 */
public class PromissoryNoteTest extends LetterTest<Money>{

	@Override
	public PromissoryNote createLetter() {
		return new PromissoryNote(sender, receiver);
	}

	@Override
	public PromissoryNote createLetter(Money content) {
		return new PromissoryNote(sender, receiver, content);
	}
	
	/**
	 * Tests sending an affordable promissory note
	 */
	@Test
	public void sendAffordablePromissoryNoteTest() throws LetterDeliveryException {
		double receiverBankAccount = receiver.getBankAccount();
		PromissoryNote promissoryNote = createLetter(new Money(2000d));
		sendLetter(promissoryNote, 1, 1, 1, 1, promissoryNote.getContent().getAmount());
		assertEquals(receiverBankAccount + promissoryNote.getContent().getAmount() - 1 /* 1 is the cost of Thanks letter */, receiver.getBankAccount(), 0);
		assertEquals(21.0, promissoryNote.getCost(), 0);
	}
	
	/**
	 * Tests if a promissory note indeed contains Money
	 */
	@Test
	public void contentInPromissoryNoteIsMoney() {
		@SuppressWarnings("unused")
		Money money = new PromissoryNote(sender, receiver).getContent();
	}
	
}

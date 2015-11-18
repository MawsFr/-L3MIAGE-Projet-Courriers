package content.specialletter;

import static org.junit.Assert.assertEquals;
import model.content.Text;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;
import model.content.specialletter.RegisteredLetter;
import model.content.specialletter.UrgentLetter;

import org.junit.Test;

import exceptions.LetterDeliveryException;

public class UrgentLetterTest extends SpecialLetterTest<Letter<?>>{
	@Override
	public UrgentLetter<Letter<?>> createLetter() {
		return createLetter(new SimpleLetter(sender, receiver));
	}
	
	@Override
	public UrgentLetter<Letter<?>> createLetter(Letter<?> content) {
		return new UrgentLetter<Letter<?>>(content);
	}

	@Test
	public void whatAmITesting() {
		SimpleLetter simpleLetter = new SimpleLetter(sender, receiver);
		UrgentLetter<SimpleLetter> letter = new UrgentLetter<SimpleLetter>(simpleLetter);
		@SuppressWarnings("unused")
		Text text = letter.getContent().getContent();
	}
	
	@Test
	public void simpleLetterInUrgentLetter() throws LetterDeliveryException{
		UrgentLetter<Letter<?>> urgentSimpleLetter = createLetter();
		assertEquals(2*1, urgentSimpleLetter.getCost(), 0);
		sendLetter(urgentSimpleLetter, 1, 0, 0, 1, 0);
	}
	
	@Test
	public void promissoryLetterInUrgentLetter() throws LetterDeliveryException{
		double receiverBankAccount = receiver.getBankAccount();
		PromissoryNote promissoryNote = new PromissoryNote(sender, receiver, 1000d);
		UrgentLetter<Letter<?>> urgentPromissoryNote = createLetter(promissoryNote);
		assertEquals(11.0, promissoryNote.getCost(), 0);
		assertEquals(2*(1 + 10), urgentPromissoryNote.getCost(), 0);
		sendLetter(urgentPromissoryNote, 1, 1, 1, 1, promissoryNote.getContent().getAmount());
		assertEquals(receiverBankAccount + promissoryNote.getContent().getAmount() - 1 /* 1 is the cost of Thanks letter */, receiver.getBankAccount(), 0);
	}

	@Test
	public void simpleLetterInRegisteredLetterInUrgentLetter() throws LetterDeliveryException{
		RegisteredLetter<Letter<?>> registeredLetter = new RegisteredLetter<Letter<?>>(new SimpleLetter(sender, receiver));
		UrgentLetter<Letter<?>> urgentRegisteredSimpleLetter = createLetter(registeredLetter);
		assertEquals(2*(1+15), urgentRegisteredSimpleLetter.getCost(),0);
		sendLetter(urgentRegisteredSimpleLetter, 1, 1, 1, 1, 0);
	}
	
	@Test
	public void promissoryLetterInRegisteredLeterInUrgentLetter() throws LetterDeliveryException{
		
		double receiverBankAccount = receiver.getBankAccount();
		PromissoryNote promissoryNote = new PromissoryNote(sender, receiver, 1000d);
		RegisteredLetter<Letter<?>> registeredLetter = new RegisteredLetter<Letter<?>>(promissoryNote);
		UrgentLetter<Letter<?>> urgentRegisteredPromissoryNote = createLetter(registeredLetter);
		assertEquals(11.0, promissoryNote.getCost(), 0);
		assertEquals(2*(1 + 10+15), urgentRegisteredPromissoryNote.getCost(), 0);
		sendLetter(urgentRegisteredPromissoryNote, 1, 2, 2, 1, promissoryNote.getContent().getAmount());
		assertEquals(receiverBankAccount + promissoryNote.getContent().getAmount() - 2 /* 2 is the cost of Thanks letter and acknowledgment of receipt*/, receiver.getBankAccount(), 0);
	}
}
package content.specialletter;

import static org.junit.Assert.assertEquals;
import model.content.Money;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;
import model.content.specialletter.RegisteredLetter;

import org.junit.Test;

import exceptions.LetterDeliveryException;

public class RegisteredLetterTest extends SpecialLetterTest<Letter<?>>{

	@Override
	public RegisteredLetter<Letter<?>> createLetter() {
		return createLetter(new SimpleLetter(sender, receiver));
	}

	@Override
	public RegisteredLetter<Letter<?>> createLetter(Letter<?> content) {
		return new RegisteredLetter<Letter<?>>(content);
	}

	@Test
	public void receiverSendsAcknowledgment() throws LetterDeliveryException {
		assertEquals(0, receiver.getNumberOfLetterSent());
		createLetter().doAction();
		assertEquals(1, receiver.getNumberOfLetterSent());
	}

	@Test
	public void sendAffordableRegisteredSimpleLetterTest() throws LetterDeliveryException {
		RegisteredLetter<Letter<?>> registeredSimpleLetter = createLetter();
		assertEquals(16, registeredSimpleLetter.getCost(), 0);
		sendLetter(registeredSimpleLetter, 1, 1, 1, 1, 0);
	}



}
package content.specialletter;

import static org.junit.Assert.assertEquals;
import model.content.letter.Letter;
import model.content.letter.SimpleLetter;
import model.content.specialletter.RegisteredLetter;

import org.junit.Test;

public class RegisteredLetterTest extends SpecialLetterTest<Letter<?>>{
	
	@Override
	public Letter<?> createLetter() {
		return createLetter(new SimpleLetter(sender, receiver));
	}
	
	@Override
	public Letter<?> createLetter(Letter<?> content) {
		return new RegisteredLetter<Letter<?>>(content);
	}
	
	@Test
	public void receiverSendsAcknowledgment() {
		assertEquals(0, receiver.getNumberOfLetterSent());
		createLetter().doAction();
		assertEquals(1, receiver.getNumberOfLetterSent());
	}



}
package content.specialletter;

import static org.junit.Assert.assertEquals;
import model.content.letter.Letter;

import org.junit.Test;

import content.letter.LetterTest;

public abstract class SpecialLetterTest<L extends Letter<?>> extends LetterTest<L> {
	
	@Test
	public void sameSenderAndReceiverAsContainedLetterTest() {
		Letter<?> letter = createLetter();
		assertEquals(letter.getSender(), ((Letter<?>) letter.getContent()).getSender());
		assertEquals(letter.getReceiver(), ((Letter<?>) letter.getContent()).getReceiver());
	}
	
}

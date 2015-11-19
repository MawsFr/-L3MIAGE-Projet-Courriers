package content.specialletter;

import static org.junit.Assert.assertEquals;
import model.content.letter.Letter;

import org.junit.Test;

import content.letter.LetterTest;

/**
 * This class tests special letters
 * @param <L> The contained letter type of the Specialletter to create
 */
public abstract class SpecialLetterTest<L extends Letter<?>> extends LetterTest<L> {
	
	/**
	 * Tests if the sender and the receiver of the special letter are the same as the sender and receiver of the container letter
	 */
	@Test
	public void sameSenderAndReceiverAsContainedLetterTest() {
		Letter<?> letter = createLetter();
		assertEquals(letter.getSender(), ((Letter<?>) letter.getContent()).getSender());
		assertEquals(letter.getReceiver(), ((Letter<?>) letter.getContent()).getReceiver());
	}
	
}

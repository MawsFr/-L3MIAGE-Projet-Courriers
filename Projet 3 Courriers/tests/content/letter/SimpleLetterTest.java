package content.letter;

import model.content.Text;
import model.content.letter.Letter;
import model.content.letter.SimpleLetter;

import org.junit.Test;

import exceptions.LetterDeliveryException;

/**
 * This class tests the simple letter class
 */
public class SimpleLetterTest extends LetterTest<Text> {

	@Override
	public SimpleLetter createLetter() {
		return new SimpleLetter(sender, receiver);
	}
	
	@Override
	public SimpleLetter createLetter(Text content) {
		return new SimpleLetter(sender, receiver, content);
	}
	
	/**
	 * @param content The text content
	 * @return A simple letter with a text
	 */
	public SimpleLetter createLetter(String content) {
		return createLetter(new Text(content));
	}
	
	/**
	 * Tests if a simple letter indeed contains text
	 */
	@Test
	public void contentInSimpleLetterIsText() {
		@SuppressWarnings("unused")
		Text text = new SimpleLetter(sender, receiver).getContent();
	}
	
	
	/**
	 * Tests sending an affordable simple letter
	 */
	@Test
	public void sendAffordableSimpleLetterTest() throws LetterDeliveryException {
		Letter<?> simpleLetter = createLetter("Bonjour");
		sendLetter(simpleLetter, 1, 0, 0, 1, 0);
	}
	
}

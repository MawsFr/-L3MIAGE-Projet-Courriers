package content.letter;

import model.content.Text;
import model.content.letter.Letter;
import model.content.letter.SimpleLetter;

import org.junit.Test;

import exceptions.LetterDeliveryException;

public class SimpleLetterTest extends LetterTest<Text> {

	@Override
	public SimpleLetter createLetter() {
		return new SimpleLetter(sender, receiver);
	}
	
	@Override
	public SimpleLetter createLetter(Text content) {
		return new SimpleLetter(sender, receiver, content);
	}
	
	public SimpleLetter createLetter(String content) {
		return createLetter(new Text(content));
	}
	
	@Test
	public void ContentInSimpleLetterIsText() {
		@SuppressWarnings("unused")
		Text text = new SimpleLetter(sender, receiver).getContent();
	}
	
	@Test
	public void sendAffordableSimpleLetterTest() throws LetterDeliveryException {
		Letter<?> simpleLetter = createLetter("Bonjour");
		sendLetter(simpleLetter, 1, 0, 0, 1, 0);
	}
	

}

package content.letter;

import static org.junit.Assert.*;
import model.content.Text;
import model.content.letter.Letter;
import model.content.letter.SimpleLetter;

import org.junit.Test;

public class SimpleLetterTest extends LetterTest<Text> {

	@Override
	public Letter<?> createLetter() {
		return new SimpleLetter(sender, receiver);
	}
	
	@Override
	public Letter<?> createLetter(Text content) {
		return new SimpleLetter(sender, receiver, content);
	}
	
	@Test
	public void ContentInSimpleLetterIsText() {
		@SuppressWarnings("unused")
		Text text = new SimpleLetter(sender, receiver).getContent();
	}
	

}

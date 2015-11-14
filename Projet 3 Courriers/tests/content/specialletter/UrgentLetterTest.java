package content.specialletter;

import model.content.Text;
import model.content.letter.Letter;
import model.content.letter.SimpleLetter;
import model.content.specialletter.UrgentLetter;

import org.junit.Test;

public class UrgentLetterTest extends SpecialLetterTest<Letter<?>>{
	@Override
	public Letter<?> createLetter() {
		return createLetter(new SimpleLetter(sender, receiver));
	}
	
	@Override
	public Letter<?> createLetter(Letter<?> content) {
		return new UrgentLetter<Letter<?>>(content);
	}

	@Test
	public void whatAmITesting() {
		SimpleLetter simpleLetter = new SimpleLetter(sender, receiver);
		UrgentLetter<SimpleLetter> letter = new UrgentLetter<SimpleLetter>(simpleLetter);
		@SuppressWarnings("unused")
		Text text = letter.getContent().getContent();
	}


}
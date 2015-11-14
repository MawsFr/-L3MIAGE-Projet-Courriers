package content.letter;

import model.content.Money;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;

public class PromissoryNoteTest extends LetterTest<Money>{

	@Override
	public Letter<?> createLetter() {
		return new PromissoryNote(sender, receiver);
	}

	@Override
	public Letter<?> createLetter(Money content) {
		return new PromissoryNote(sender, receiver, content);
	}

}

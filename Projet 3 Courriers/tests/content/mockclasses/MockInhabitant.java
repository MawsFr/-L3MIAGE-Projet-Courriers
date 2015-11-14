package content.mockclasses;

import model.city.City;
import model.city.Inhabitant;
import model.content.letter.Letter;

public class MockInhabitant extends Inhabitant {

	protected int numberOfLetterSent;
	protected int numberOfLetterReceived;
	
	
	public MockInhabitant(City city) {
		super("MockInhabitant", city);
	}
	
	@Override
	public void sendLetter(Letter<?> letter) {
		super.sendLetter(letter);
		numberOfLetterSent++;
		
	}
	
	@Override
	public void receiveLetter(Letter<?> letter) {
		super.receiveLetter(letter);
		numberOfLetterReceived++;
	}
	
	public int getNumberOfLetterSent() {
		return numberOfLetterSent;
	}
	
	public int getNumberOfLetterReceived() {
		return numberOfLetterReceived;
	}
	
	
	
	
	
	

}

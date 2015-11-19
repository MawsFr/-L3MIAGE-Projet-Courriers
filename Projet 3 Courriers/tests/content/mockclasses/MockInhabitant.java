package content.mockclasses;

import exceptions.LetterDeliveryException;
import model.city.City;
import model.city.Inhabitant;
import model.content.letter.Letter;

/**
 * This class is a sub class of inhabitant which contains informations about the number of sent and received letters
 */
public class MockInhabitant extends Inhabitant {

	/**
	 * The number of sent letters
	 */
	protected int numberOfLetterSent;
	
	/**
	 * The number of received letters 
	 */
	protected int numberOfLetterReceived;
	
	
	/**
	 * Constructor with city
	 * @param city The coty this inhabitant belongs to
	 */
	public MockInhabitant(City city) {
		super("MockInhabitant", city);
	}
	
	/* (non-Javadoc)
	 * @see model.city.Inhabitant#sendLetter(model.content.letter.Letter)
	 */
	@Override
	public void sendLetter(Letter<?> letter) throws LetterDeliveryException {
		super.sendLetter(letter);
		numberOfLetterSent++;
	}
	
	/* (non-Javadoc)
	 * @see model.city.Inhabitant#receiveLetter(model.content.letter.Letter)
	 */
	@Override
	public void receiveLetter(Letter<?> letter) throws LetterDeliveryException {
		super.receiveLetter(letter);
		numberOfLetterReceived++;
	}
	
	/**
	 * @return The number of sent letters
	 */
	public int getNumberOfLetterSent() {
		return numberOfLetterSent;
	}
	
	/**
	 * @return The number of received letters
	 */
	public int getNumberOfLetterReceived() {
		return numberOfLetterReceived;
	}
	
	
	
	
	
	

}

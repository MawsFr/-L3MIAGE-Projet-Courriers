package content.letter;

import model.city.City;
import model.content.Content;
import model.content.letter.Letter;

import org.junit.Before;
import org.junit.Test;

import content.mockclasses.MockInhabitant;
import static org.junit.Assert.*;


public abstract class LetterTest<C extends Content> {
	
	protected City city1, city2;
	protected MockInhabitant sender;
	protected MockInhabitant receiver;
	
	@Before
	public void setUp() {
		this.city1 = new City("City 1");
		this.city2 = new City("City 2");
		sender = new MockInhabitant(city1);
		receiver = new MockInhabitant(city2);
	}
	
	public abstract Letter<?> createLetter();
	public abstract Letter<?> createLetter(C content);
	
	@Test
	public void allLetterHasAPositiveCost() {
		Letter<?> letter = createLetter();
		assertTrue(letter.getCost() > 0);
	}
	

}

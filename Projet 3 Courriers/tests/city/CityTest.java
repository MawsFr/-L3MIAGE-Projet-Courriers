package city;

import static org.junit.Assert.assertEquals;
import model.city.City;
import model.city.Inhabitant;

import org.junit.Before;
import org.junit.Test;

import exceptions.LetterDeliveryException;

/**
 * This class tests the City class 
 */
public class CityTest {
	
	/**
	 * A city attribute
	 */
	protected City city;
	
	/**
	 * Another city attribute 
	 */
	protected City city2;
	
	/**
	 * Sets up some variables
	 */
	@Before
	public void setUp() {
		city = new City("City", 2);
		city2 = new City("City 2", 2);

	}
	
	/**
	 * Tests a city with a null name
	 * @throws NullPointerException Because the name is null
	 */
	@Test(expected=NullPointerException.class)
	public void cityWithNullNameTest() {
		city = new City(null);
	}
	
	/**
	 * Tests a city with an empty name
	 * @throws IllegalArgumentException Because the name is empty 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void cityWithEmptyNameTest() {
		city = new City("");
	}
	
	/**
	 * Tests a city with a negative number of inhabitant to create
	 * @throws IllegalArgumentException Because of the negative number
	 */
	@Test(expected=IllegalArgumentException.class)
	public void cityWithNegativeNbOfInhabitant() {
		city = new City("City", -1);
	}
	
	/**
	 * Tests a city with a null number of inhabitant to create
	 * @throws IllegalArgumentException Because of the null number
	 */
	@Test
	public void cityWithZeroNbOfInhabitant() {
		city = new City("City", 0);
		assertEquals(0, city.getNbInhabitants());
	}
	
	/**
	 * Tests sending a null argument for letter
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void sendNullLetterTest() {
		city.sendLetter(null);
	}
	
	/**
	 * Tests if it indeed throws an exception when we try to distribute letters when the post box is empty
	 * (and if you really read all comments lol) 
	 * PS: Si vous lisez ce commentaire, envoyez nous un 
	 * petit e-mail. Car si vous le faites c'est que vous avez
	 * vraiment plus de courage que nous tous réunis !! Et bon 
	 * courage pour la suite de la lecture xD   
	 */
	@Test(expected=LetterDeliveryException.class)
	public void distributeEmptyPostBoxTest() throws LetterDeliveryException {
		assertEquals(0, city.getNbLettersInPostBox());
		city.distibuteLetters();
	}
	
	/**
	 * Tests adding null inhabitant
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void addNullInhabitantTest() {
		city.addInhabitant(null);
	}
	
	/**
	 * Tests adding inhabitant from another city
	 */
	@Test
	public void addInhabitantFromDifferentCityTest() {
		Inhabitant inhabitant1 = city2.getInhabitants().get(0);
		city.addInhabitant(inhabitant1);
		assertEquals(3, city.getNbInhabitants());
		assertEquals(city, inhabitant1.getCity());
	}
	
	/**
	 * Tests adding an inhabitant to the same city he belongs to
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void addAlreadyRegisteredInhabitantTest() {
		city.addInhabitant(city.getInhabitants().get(0));
	}
	
	/**
	 * Tests removing null inhabitant
	 * @throws NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void removeNullInhabitantTest() {
		city.removeInhabitant(null);
	}

	/**
	 * Tests removing an inhabitant from a city he doesn't belong to
	 */
	@Test(expected=IllegalArgumentException.class)
	public void removeInhabitantFromOtherCityTest() {
		Inhabitant inhabitant1 = city2.getInhabitants().get(0);
		city.removeInhabitant(inhabitant1);
	}
}

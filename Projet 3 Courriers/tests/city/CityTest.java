package city;

import static org.junit.Assert.assertEquals;
import model.city.City;
import model.city.Inhabitant;

import org.junit.Before;
import org.junit.Test;

import exceptions.LetterDeliveryException;

public class CityTest {
	
	protected City city;
	protected City city2;
	
	@Before
	public void setUp() {
		city = new City("City", 2);
		city2 = new City("City 2", 2);

	}
	
	@Test(expected=NullPointerException.class)
	public void cityWithNullNameTest() {
		city = new City(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cityWithEmptyNameTest() {
		city = new City("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cityWithNegativeNbOfInhabitant() {
		city = new City("City", -1);
	}
	
	@Test
	public void cityWithZeroNbOfInhabitant() {
		city = new City("City", 0);
		assertEquals(0, city.getNbInhabitants());
	}
	
	@Test(expected=NullPointerException.class)
	public void sendNullLetterTest() {
		city.sendLetter(null);
	}
	
	@Test(expected=LetterDeliveryException.class)
	public void distributeEmptyPostBoxTest() throws LetterDeliveryException {
		assertEquals(0, city.getNbLettersInPostBox());
		city.distibuteLetters();
	}
	
	@Test(expected=NullPointerException.class)
	public void addNullInhabitantTest() {
		city.addInhabitant(null);
	}
	
	@Test
	public void addInhabitantFromDifferentCityTest() {
		Inhabitant inhabitant1 = city2.getInhabitants().get(0);
		city.addInhabitant(inhabitant1);
		assertEquals(3, city.getNbInhabitants());
		assertEquals(city, inhabitant1.getCity());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addAlreadyRegisteredInhabitantTest() {
		city.addInhabitant(city.getInhabitants().get(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void removeNullInhabitantTest() {
		city.removeInhabitant(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void removeInhabitantFromOtherCityTest() {
		Inhabitant inhabitant1 = city2.getInhabitants().get(0);
		city.removeInhabitant(inhabitant1);
	}
}

package city;
import model.city.City;

import org.junit.Before;
import org.junit.Test;

import view.ConsoleView;
import view.Simulation;
import content.mockclasses.MockInhabitant;


/**
 * This class tests the simulation class
 */
public class SimulationTest {

	/**
	 * A console view
	 */
	protected ConsoleView consoleView;
	
	/**
	 * A simulation object
	 */
	protected Simulation simulation;
	
	/**
	 * Sets some variables
	 */
	@Before
	public void setUp() {
		consoleView = new ConsoleView();
		simulation = new Simulation(consoleView, 2);
	}
	
	/**
	 * Tests creating a simulation with a null console view argument
	 */
	@Test(expected=NullPointerException.class)
	public void nullConsolViewTest() {
		this.simulation = new Simulation(null, 1);
	}
	
	/**
	 * Tests creating a simulation with negative number of days
	 */
	@Test(expected=IllegalArgumentException.class)
	public void negativeNbDaysTest() {
		this.simulation = new Simulation(consoleView, -1);
	}
	
	/**
	 * Tests creating a simulation with null number of days
	 */
	@Test(expected=IllegalArgumentException.class)
	public void nullNbDaysTest() {
		this.simulation = new Simulation(consoleView, 0);
	}
	
	/**
	 * Tests creating a simulation with odd number of days
	 * @throws IllegalArgumentException Because we'll have send letters not distributed
	 */
	@Test(expected=IllegalArgumentException.class)
	public void oddNumberNbDaysTest() {
		this.simulation = new Simulation(consoleView, 3);
	}
	
	/**
	 * Tests getting a letter with null sender
	 */
	@Test(expected=NullPointerException.class)
	public void nullSendertest() {
		simulation.getRandomLetter(null, null);
	}


	/**
	 * Tests getting a letter with null receiver
	 */
	@Test(expected=NullPointerException.class)
	public void nullReceiverTest() {
		simulation.getRandomLetter(new MockInhabitant(new City("City")), null);
	}
	
}

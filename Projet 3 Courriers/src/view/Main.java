package view;



/**
 * Main class
 */
public class Main {

	/**
	 * Main function that runs the simulation
	 * @param args arguments
	 */
	public static void main(String[] args) {
		ConsoleView consoleView = new ConsoleView();
		Simulation simulation = new Simulation(consoleView, 6);
		try {
			simulation.run();
		} catch (Exception e) {
			consoleView.printlnMessage(e.getMessage());
		}
		

	}
	
}

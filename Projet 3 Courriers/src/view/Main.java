package view;



public class Main {

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

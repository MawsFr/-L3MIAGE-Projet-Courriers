package view;

import model.City;

public class Main {
	
	public static void main(String[] args) {
		ConsoleView consoleView = new ConsoleView();
		City city = new City("BoobsVille", 100);
		
		city.addObserver(consoleView);
		
	}

}

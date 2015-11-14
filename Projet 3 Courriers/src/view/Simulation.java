package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.city.City;
import model.city.Inhabitant;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;
import model.content.specialletter.RegisteredLetter;
import model.content.specialletter.UrgentLetter;
import model.observable.ExtendedObservable;

public class Simulation extends ExtendedObservable {

	protected int nbDays;
	protected ConsoleView consoleView;
	protected City city;
	protected Random r;
	
	protected List<Inhabitant> tempInhabitants;
	
	
	public Simulation(ConsoleView consoleView, int nbDays) {
		this.consoleView = consoleView;
		this.nbDays = nbDays;
		r = new Random();
		tempInhabitants = new ArrayList<Inhabitant>();
		
		createCity("BoobsVille", 100);

	
		
	}
	
	protected void createCity(String name, int nbInhabitants) {
		this.addObserver(consoleView);
		
		notify("Creating " + city + " city");
		notify("Creating " + nbInhabitants + " inhabitants");
		
		this.city = new City(name, nbInhabitants);
		city.addObserver(consoleView);
		for(Inhabitant inhabitant : city.getInhabitants()) {
			inhabitant.addObserver(consoleView);
		}
	}

	public void run() {
		Inhabitant sender, receiver;
		int randNbInhabitant = 0;
		
		do {
			randNbInhabitant = r.nextInt(city.getNbInhabitants()) / 2;
		} while(randNbInhabitant == 0);
		
		notify("Mailing letters for " + nbDays + " days");
		
		for(int i = 1; i <= nbDays; i++) {
			resetTempList();
			notify("**************************************************");
			notify("Day " + i);
			if((i - 1) % 2 == 0) {
				
				for(int j = 0; j < randNbInhabitant; j++) {
					sender = getRandomInhabitant();
					receiver = getRandomInhabitant();
					sender.sendLetter(getRandomLetter(sender, receiver));
				}
			} else {
				city.distibuteLetters();
			}
			
		}
		
	}
	
	private void resetTempList() {
		this.tempInhabitants.clear();
		for(Inhabitant inhabitant : city.getInhabitants()) {
			tempInhabitants.add(inhabitant);
		}
	}

	protected Letter<?> getRandomLetter(Inhabitant sender, Inhabitant receiver) {
		//TODO : Check if inhabitant has enough money to do a promissory note
		Letter<?> letter = null;
		int randLetterType = r.nextInt(3); //0 = Simple Letter | 1 = PromissoryNote | 2 = Registered Letter
		switch(randLetterType) {
		case 1:
			letter = new PromissoryNote(sender, receiver, r.nextInt((int) sender.getBankAccount()));
			break;
			
		case 2 :
			letter = new SimpleLetter(sender, receiver, "bla bla");
			break;
			
		default :
			int randContainedLetter = r.nextInt(2); //0 = Simple letter | 1 = PromissoryNote
			switch(randContainedLetter) {
			case 1 :
				letter = new RegisteredLetter<Letter<?>>(new PromissoryNote(sender, receiver, r.nextInt((int) sender.getBankAccount())));
				break;
				
			default :
				letter = new RegisteredLetter<Letter<?>>(new SimpleLetter(sender, receiver, "blabla"));
				break;
			}
			
			break;
			
		}
		
		//assert(letter != null);
		
		int randSpecialityType;
		
		if(randLetterType == 2) { //if letter is a registered letter we don't wan't another registered letter to contain it
			randSpecialityType = r.nextInt(2); //0 = Not Special | 1 = Urgent Letter
		} else {
			randSpecialityType = r.nextInt(2); //0 = Not Special | 1 = Urgent Letter | 2 = Registered letter
		}
		
		switch (randSpecialityType) {
		case 1 :
			letter = new UrgentLetter<Letter<?>>(letter);
			break;

		case 2 :
			letter = new RegisteredLetter<Letter<?>>(letter);
			break;
		default:
			break;
		}
		
		return letter;
	}
	
	protected Inhabitant getRandomInhabitant() {
		return tempInhabitants.remove(r.nextInt(tempInhabitants.size()));
	}

}

package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exceptions.LetterDeliveryException;
import model.city.City;
import model.city.Inhabitant;
import model.content.letter.Letter;
import model.content.letter.PromissoryNote;
import model.content.letter.SimpleLetter;
import model.content.specialletter.RegisteredLetter;
import model.content.specialletter.UrgentLetter;
import model.observable.ExtendedObservable;

/**
 * This class simulate the logic of the software
 * 
 * see run function comment
 */
public class Simulation extends ExtendedObservable {

	/**
	 * The number of days of the simulation
	 */
	protected int nbDays;
	
	/**
	 * The console view 
	 */
	protected ConsoleView consoleView;
	
	/**
	 * The city where we simulate 
	 */
	protected City city;
	
	/**
	 * A random number genertor 
	 */
	protected Random r;
	
	/**
	 * A temps list of the city's inhabitant to help choosing random inhabitants
	 */
	protected List<Inhabitant> tempInhabitants;
	
	
	/**
	 * Constructor with console view and number of days for the simulation
	 * @param consoleView The console view
	 * @param nbDays The number of days for the simulation
	 */
	public Simulation(ConsoleView consoleView, int nbDays) {
		if(consoleView == null) {
			throw new NullPointerException("You must specify a non null consoleView");
		}
		
		if(nbDays <= 0) {
			throw new IllegalArgumentException("You must specify a positive number !");
		}
		
		if(nbDays % 2 != 0) {
			throw new IllegalArgumentException("You must specify an even number !"); //We want to distribute letters sent the last time
		}
		
		this.consoleView = consoleView;
		this.nbDays = nbDays;
		r = new Random();
		tempInhabitants = new ArrayList<Inhabitant>();
		
		createCity("KebabCity", 100);
		
	}
	
	/**
	 * Creates the city and its inhabitants
	 * @param name The name of the city
	 * @param nbInhabitants The number of inhabitants
	 */
	protected void createCity(String name, int nbInhabitants) {
		if(name == null) {
			throw new NullPointerException("You must specify a non null name for the city");
		}
		
		this.addObserver(consoleView);
		
		notify("Creating " + city + " city");
		notify("Creating " + nbInhabitants + " inhabitants");
		
		this.city = new City(name, nbInhabitants);
		city.addObserver(consoleView);
		for(Inhabitant inhabitant : city.getInhabitants()) {
			inhabitant.addObserver(consoleView);
		}
	}

	/**
	 * Runs the simulation
	 * Chooses a random number of days (recalculated each time)
	 * Chooses a random number of sender and receiver (same number for both)
	 * Chooses a random sender
	 * Chooses a random receiver
	 * Chooses a random letter with a container or not
	 * sender sends the letter
	 * city distributes the letter
	 * receiver receives the letter
	 * receiver sends back special letter
	 * @throws LetterDeliveryException
	 */
	public void run() throws LetterDeliveryException {
		Inhabitant sender, receiver;
		int randNbInhabitant = 0;
		
		
		
		notify("Mailing letters for " + nbDays + " days");
		
		for(int i = 1; i <= nbDays; i++) {
			
			resetTempList();
			notify("**************************************************");
			notify("Day " + i);
			if((i - 1) % 2 == 0) { //If we are in an even days
				//We choose the number of sender and receiver
				do { 
					randNbInhabitant = r.nextInt(city.getNbInhabitants()) / 2;
				} while(randNbInhabitant == 0);
				
				//We send letters
				for(int j = 0; j < randNbInhabitant; j++) {
					sender = getRandomInhabitant();
					receiver = getRandomInhabitant();
					sender.sendLetter(getRandomLetter(sender, receiver));
				}
			} else { //if we are in an odd day
				city.distibuteLetters();
			}
			
		}
		
	}
	
	/**
	 * Resets the temp list
	 */
	protected void resetTempList() {
		this.tempInhabitants.clear();
		for(Inhabitant inhabitant : city.getInhabitants()) {
			tempInhabitants.add(inhabitant);
		}
	}

	/**
	 * @param sender The sender of the letter
	 * @param receiver The receiver of the letter
	 * @return A random letter with containing letter or not
	 */
	public Letter<?> getRandomLetter(Inhabitant sender, Inhabitant receiver) {
		if(sender == null) {
			throw new NullPointerException("You must specify a non null sender");
		}
		
		if(receiver == null) {
			throw new NullPointerException("You must specify a non null receiver");
		}
		
		Letter<?> letter = null;
		
		//Choosing contained letter
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
		
		
		//Choosing container
		int randSpecialityType;
		
		if(randLetterType == 2) { //if letter is a registered letter we don't wan't another registered letter to contain it
			randSpecialityType = r.nextInt(2); //0 = Not Special | 1 = Urgent Letter
		} else { //it's just a simple letter or promissory note
			randSpecialityType = r.nextInt(3); //0 = Not Special | 1 = Urgent Letter | 2 = Registered letter
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
	
	/**
	 * @return A random inhabitant frome the temp list
	 */
	public Inhabitant getRandomInhabitant() {
		return tempInhabitants.remove(r.nextInt(tempInhabitants.size()));
	}

}

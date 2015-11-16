package model.city;

import java.util.ArrayList;
import java.util.List;

import exceptions.LetterDeliveryException;
import model.content.letter.Letter;
import model.observable.ExtendedObservable;

public class City extends ExtendedObservable {

	protected String name;
	protected List<Inhabitant> inhabitants;
	protected List<Letter<?>> postBox;
	
	public City(String name) {
		this(name, 0);
	}
	
	public City(String name, int nbInabitants) {
		if(name == null) {
			throw new NullPointerException("You must specify a non null parameter for city's name");
		}
		
		if(name.isEmpty()) {
			throw new IllegalArgumentException("You must specify a non empty parameter for city's name");
		}
		
		if(nbInabitants < 0) {
			throw new IllegalArgumentException("You must specify a positive or null number of inhabitants to create");
		}
		
		this.name = name;
		this.inhabitants = new ArrayList<Inhabitant>();
		postBox = new ArrayList<Letter<?>>();
		
		for(int i = 1; i <= nbInabitants; i++) {
			this.inhabitants.add(new Inhabitant("Inhabitant-" + i, this));
		}
	}

	public void sendLetter(Letter<?> letter){
		if(letter == null) {
			throw new NullPointerException("You must specify a non null letter to send argument");
		}
		
		notify("-> " + letter.getSender() + " mails " + letter + " to " + letter.getReceiver() + " for a cost of " + letter.getCost() + ((letter.getCost() > 1) ? " euros" : " euro"));;
		this.postBox.add(letter);
	}
	
	public void distibuteLetters() throws LetterDeliveryException {
		if(postBox.isEmpty()) {
			throw new LetterDeliveryException("There are no letters to distribute !");
		}
		/* Duplicating the list because after receiving a registered letter, the receiver has to send back an aknolegment of receipt 
		 * (or a thanks letter after receiving a promissory note) so it may throw a concurrentmodificationexception exception 
		 * if we add a letter while iteraing on postBox
		 * */ 
		List<Letter<?>> lettersToDistribute = new ArrayList<Letter<?>>();
		for(Letter<?> letter : postBox) {
			lettersToDistribute.add(letter);
		}
		
		postBox.clear(); //We clear the list so if we add a thanks letter it won't be cleared after iterating on lettersToDistribute list
		
		for(Letter<?> letter : lettersToDistribute) {
			notify("<- " + letter.getReceiver() + " receives " + letter +" from " + letter.getSender());
			letter.getReceiver().receiveLetter(letter);
		}
	}
	
	public void addInhabitant(Inhabitant inhabitant) {
		if(inhabitant == null) {
			throw new NullPointerException("You must specify a non null inhabitant argument to add to the city");
		}
		
		if(inhabitant.getCity() != null && inhabitant.getCity() != this) {
			inhabitant.getCity().removeInhabitant(inhabitant);
		}
		
		if(this.inhabitants.contains(inhabitant)) {
			throw new IllegalArgumentException("this inhabitant is already registered in this city");
		}
		
		this.inhabitants.add(inhabitant);
		inhabitant.setCity(this);
	}
	
	public void removeInhabitant(Inhabitant inhabitant) {
		if(inhabitant == null) {
			throw new NullPointerException("You must specify a non null Inhabitant to remove from the city");
		}
		
		if(!this.inhabitants.contains(inhabitant)) {
			throw new IllegalArgumentException("This inhabitant doesn't belong to this city !");
		}
		
		this.inhabitants.remove(inhabitant);
	}
	
	public boolean hasLettersToSend() {
		return this.postBox.size() > 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNbInhabitants() {
		return this.inhabitants.size();
	}
	
	public List<Inhabitant> getInhabitants() {
		return inhabitants;
	}
	
	public List<Letter<?>> getPostBox() {
		return postBox;
	}
	
	public int getNbLettersInPostBox() {
		return postBox.size();
	}
	
	@Override
	public String toString() {
		return name;
	}
}

package model.city;

import java.util.ArrayList;
import java.util.List;

import model.content.letter.Letter;
import model.observable.ExtendedObservable;

public class City extends ExtendedObservable {

	protected String name;
	protected List<Inhabitant> inhabitants;
	protected List<Letter<?>> postBox;
	
	public City(String name, int nbInabitants) {
		this.name = name;
		this.inhabitants = new ArrayList<Inhabitant>();
		postBox = new ArrayList<Letter<?>>();
		
		for(int i = 1; i <= nbInabitants; i++) {
			this.inhabitants.add(new Inhabitant("Inhabitant-" + i, this));
		}
	}

	public void sendLetter(Letter<?> letter){
		notify("-> " + letter.getSender() + " mails " + letter + " to " + letter.getReceiver() + " for a cost of " + letter.getCost() + ((letter.getCost() > 1) ? " euros" : " euro"));;
		this.postBox.add(letter);
	}
	
	public void distibuteLetters() {
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
	
	public String getName() {
		return name;
	}
	
	public int getNbInhabitants() {
		return this.inhabitants.size();
	}
	
	public List<Inhabitant> getInhabitants() {
		return inhabitants;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

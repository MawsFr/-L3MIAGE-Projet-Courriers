package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.content.letter.Letter;

public class City extends Observable {

	protected String name;
	protected List<Inhabitant> inhabitants;
	protected List<Letter<?>> postBox;
	
	public City(String name, int nbInabitants) {
		this.name = name;
		this.inhabitants = new ArrayList<Inhabitant>();
		
		for(int i = 1; i <= nbInabitants; i++) {
			this.inhabitants.add(new Inhabitant("Inhabitant-" + i, this));
		}
	}

	public void sendLetter(Letter<?> letter){
		this.postBox.add(letter);
	}
	
	public void distibuteLetters() { 
		for(Letter<?> letter : postBox) {
			letter.getReceiver().receiveLetter(letter);
			
		}
	}
	
	public String getName() {
		return name;
	}
}

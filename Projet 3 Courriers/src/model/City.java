package model;

import java.util.List;

import model.content.letter.Letter;

public class City {

	protected String name;
	protected List<Letter<?>> postBox;
	
	public void sendLetter(Letter<?> letter){
		this.postBox.add(letter);
	}
	
	public void distibuteLetters() { 
		for(Letter<?> letter : postBox) {
			letter.getReceiver().receiveLetter(letter);
			
		}
		
	}
}

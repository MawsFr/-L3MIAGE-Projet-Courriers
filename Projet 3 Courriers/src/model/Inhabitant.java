package model;

import model.content.letter.Letter;

public class Inhabitant {

	protected String name;
	protected City city;
	protected double bankAccount;
	
	public void credit(double amount){
		bankAccount += amount;
	}
	
	public void debit(double amount){
		bankAccount -= amount;
	}
	
	public void sentdLetter(Letter<?> letter) {
		
	}
	
	public void receiveLetter(Letter<?> letter) {
		letter.doAction();
	}
}

package model;

import model.content.letter.Letter;

public class Inhabitant {

	protected String name;
	protected City city;
	protected double bankAccount;
	
	public void credit(double money){
		bankAccount += money;
	}
	
	public void debit(double money){
		bankAccount -= money;
	}
	
	public void sentdLetter(Letter<?> letter) {
		
	}
	
	public void receiveLetter(Letter<?> letter) {
		letter.doAction();
	}
}

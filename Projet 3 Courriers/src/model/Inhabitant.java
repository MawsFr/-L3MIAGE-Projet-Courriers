package model;

import model.content.letter.Letter;

public class Inhabitant {

	protected String name;
	protected City city;
	protected int bankAccount;
	
	public void credit(int money){
		bankAccount += money;
	}
	
	public void debit(int money){
		bankAccount -= money;
	}
	
	public void sentdLetter(Letter<?> letter){
		
	}
	
	public void receiveLetter(Letter<?> letter){
		
	}
}

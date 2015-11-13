package model;

import model.content.letter.Letter;

public class Inhabitant {

	protected String name;
	protected City city;
	protected double bankAccount;
	
	public Inhabitant(String name, City city) {
		this(name, city, 5000d);
	}
	
	public Inhabitant(String name, City city, double bankAccount) {
		this.name = name;
		this.city = city;
		this.bankAccount = bankAccount;
	}
	
	public void credit(double amount){
		bankAccount += amount;
	}
	
	public void debit(double amount){
		bankAccount -= amount;
	}
	
	public void sendLetter(Letter<?> letter) {
		city.sendLetter(letter);
	}
	
	public void receiveLetter(Letter<?> letter) {
		letter.doAction();
	}
	
	public double getBankAccount() {
		return bankAccount;
	}
	
	public String getName() {
		return name;
	}
	
	public City getCity() {
		return city;
	}
	
}

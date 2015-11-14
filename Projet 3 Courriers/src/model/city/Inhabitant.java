package model.city;

import model.content.letter.Letter;
import model.observable.ExtendedObservable;

public class Inhabitant extends ExtendedObservable {

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
		this.city.addInhabitant(this);
	}
	
	public void credit(double amount){
		bankAccount += amount;
		notify("\t+ " + this + " account is credited with " + amount + " euros; its balance is now " + bankAccount + " euros");
	}
	
	public void debit(double amount){
		bankAccount -= amount;
		notify("\t- " + amount + ((amount > 1)?" euros " : " euro") + "is debited from " + this + " account whose balance is now " + bankAccount + " euros");
	}
	
	public void sendLetter(Letter<?> letter) {
		city.sendLetter(letter);
		debit(letter.getCost());
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
	
	@Override
	public String toString() {
		return name;
	}
	
}

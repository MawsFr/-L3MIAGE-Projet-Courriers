package model.city;

import exceptions.LetterDeliveryException;
import model.content.letter.Letter;
import model.observable.ExtendedObservable;

public class Inhabitant extends ExtendedObservable {

	protected String name;
	protected City city;
	protected double bankAccount;
	
	public Inhabitant(String name, City city) {
		this(name, city, 5000.0d);
	}
	
	public Inhabitant(String name, City city, double bankAccount) {
		if(name == null) {
			throw new NullPointerException("You must specify a non null name for this inhabitant");
		}
		
		if(name.isEmpty()) {
			throw new IllegalArgumentException("You must specify a non empty name for this inhabitant");
		}
		
		if(city == null) {
			throw new NullPointerException("You must specify a city which this inhabitant belongs to");
		}
		
		//TODO : Si l'on autorise pas le découvert vérifier que le bankaccount est supérieu ou égal a zéro
		this.name = name;
		this.city = city;
		this.bankAccount = bankAccount;
	}
	
	public void credit(double amount){
		if(amount <= 0) {
			throw new IllegalArgumentException("You cannot credit a negative or null amount");
		}
		
		bankAccount += amount;
		notify("\t+ " + this + " account is credited with " + amount + " euros; its balance is now " + bankAccount + " euros");
	}
	
	public void debit(double amount){
		if(amount <= 0) {
			throw new IllegalArgumentException("You cannot debit a negative or null amount");
		}
		//TODO : Voir si on autorise le découvert ou pas (on devrait)
		bankAccount -= amount;
		notify("\t- " + amount + ((amount > 1)?" euros " : " euro") + "is debited from " + this + " account whose balance is now " + bankAccount + " euros");
	}
	
	public void sendLetter(Letter<?> letter) throws LetterDeliveryException {
		if(letter == null) {
			throw new NullPointerException("You must specify a non null letter argument to send");
		}
		
		if(letter.getSender() != this) {
			throw new LetterDeliveryException(name + " doesn't own the letter he's trying to send");
		}
		
		if(!letter.isAfordableBy(this)) {
			throw new LetterDeliveryException("The letter is not afordable by " + name);
		}
		
		city.sendLetter(letter);
		debit(letter.getCost());
	}
	
	public void receiveLetter(Letter<?> letter) throws LetterDeliveryException {
		if(letter == null) {
			throw new NullPointerException("You must specify a non null letter to receive");
		}
		
		if(letter.getReceiver() != this) {
			throw new LetterDeliveryException(name + " doesn't own the letter he's receiving");
		}
		
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
	
	public void setCity(City city) {
		this.city = city;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
}

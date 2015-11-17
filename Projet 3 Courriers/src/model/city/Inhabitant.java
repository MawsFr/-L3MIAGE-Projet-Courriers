package model.city;

import exceptions.LetterDeliveryException;
import model.content.letter.Letter;
import model.observable.ExtendedObservable;

/**
 * This class represents an inhabitant
 * 
 * @see City
 */
public class Inhabitant extends ExtendedObservable {

	/**
	 * The name if this inhabitant
	 */
	protected String name;
	
	/**
	 * The city which this inhabitant belongs to
	 */
	protected City city;
	
	/**
	 * The bank account of this inhabitant (can be negative)
	 */
	protected double bankAccount;
	
	/**
	 * Constructor with the name and the city of this inhabitant
	 * @param name The name of this inhabitant
	 * @param city The city which this inhabitant belongs to
	 */
	public Inhabitant(String name, City city) {
		this(name, city, 5000.0d);
	}
	
	/**
	 * Constructor with the name and the city of this inhabitant
	 * @param name The name of this inhabitant
	 * @param city The city which this inhabitant belongs to
	 * @param bankAccount The amount in the bank account of this inhabitant
	 */
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
	
	/**
	 * Credits the bank account of this inhabitant
	 * @param amount The amount to credit
	 * @throws IllegalArgumentException If the amount is negative or null
	 */
	public void credit(double amount){
		if(amount <= 0) {
			throw new IllegalArgumentException("You cannot credit a negative or null amount");
		}
		
		bankAccount += amount;
		notify("\t+ " + this + " account is credited with " + amount + " euros; its balance is now " + bankAccount + " euros");
	}
	
	/**
	 * Debits the bank account of this inhabitant
	 * @param amount The amount to debit
	 * @param IllegalArgumentException If the amount is negative or null
	 */
	public void debit(double amount){
		if(amount <= 0) {
			throw new IllegalArgumentException("You cannot debit a negative or null amount");
		}
		//TODO : Voir si on autorise le découvert ou pas (on devrait)
		bankAccount -= amount;
		notify("\t- " + amount + ((amount > 1)?" euros " : " euro") + "is debited from " + this + " account whose balance is now " + bankAccount + " euros");
	}
	
	/**
	 * Sends a letter to another inhabitant
	 * @param letter The letter to send
	 * @throws LetterDeliveryException If the sender is different of this inhabitant or if the letter isn't affordable for this inhabitant
	 */
	public void sendLetter(Letter<?> letter) throws LetterDeliveryException {
		if(letter == null) {
			throw new NullPointerException("You must specify a non null letter argument to send");
		}
		
		if(letter.getSender() != this) {
			throw new LetterDeliveryException(name + " doesn't own the letter he's trying to send");
		}
		
		if(!letter.isAffordableBy(this)) {
			throw new LetterDeliveryException("The letter is not afordable by " + name);
		}
		
		city.sendLetter(letter);
		debit(letter.getCost());
	}
	
	/**
	 * Receives a letter from another inhabitant
	 * @param letter The letter to receive
	 * @throws LetterDeliveryException If the receiver of the letter isn't this inhabitant
	 */
	public void receiveLetter(Letter<?> letter) throws LetterDeliveryException {
		if(letter == null) {
			throw new NullPointerException("You must specify a non null letter to receive");
		}
		
		if(letter.getReceiver() != this) {
			throw new LetterDeliveryException(name + " doesn't own the letter he's receiving");
		}
		
		letter.doAction();
	}
	
	/**
	 * @return The amount in the bank account of this inhabitant
	 */
	public double getBankAccount() {
		return bankAccount;
	}
	
	/**
	 * Sets bank account value
	 * @param bankAccount The value to set
	 */
	public void setBankAccount(double bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	/**
	 * @return The name of this inhabitant
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The city of this inhabitant
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * Sets the city of this inhabitant (can be null)
	 * @param city The city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
}

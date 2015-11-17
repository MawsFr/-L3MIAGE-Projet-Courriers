package model.observable;

import java.util.Observable;

/**
 * This class is an extended version of Observable which regroup setChanged and notifyObservers in one function
 * 
 * @see Observable
 */
public class ExtendedObservable extends Observable{
	
	/**
	 * @param message The message to display at screen
	 */
	public void notify(String message) {
		setChanged();
		notifyObservers(message);
	}

}

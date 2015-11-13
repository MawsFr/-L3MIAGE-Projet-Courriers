package model.observable;

import java.util.Observable;

public class ExtendedObservable extends Observable{
	
	public void notify(String message) {
		setChanged();
		notifyObservers(message);
	}

}

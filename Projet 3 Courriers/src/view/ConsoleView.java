package view;

import java.util.Observable;
import java.util.Observer;

/**
 * <b>This class respresents a Console view where all traces are printed</b>
 *
 */
public class ConsoleView implements Observer {

	/**
	 * Prints a message on a new line
	 * @param message The message to print
	 */
	public void printlnMessage(String message) {
		if(!message.isEmpty()) {
			System.out.println(message);
		}
	}

	@Override
	public void update(Observable o, Object message) {
		printlnMessage((String) message);
	}

}

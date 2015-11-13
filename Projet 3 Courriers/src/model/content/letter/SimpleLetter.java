package model.content.letter;

import model.Inhabitant;
import model.content.Text;

public class SimpleLetter extends Letter<Text> {

	public SimpleLetter(Inhabitant sender, Inhabitant receiver, String content) {
		super(sender, receiver, new Text(content));
	}

	@Override
	public void doAction() {
		
	}
	
	@Override
	public double getCost() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "a simple "  + super.toString();
	}
}

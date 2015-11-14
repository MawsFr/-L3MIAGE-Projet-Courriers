package model.content.letter;

import model.city.Inhabitant;
import model.content.Text;

public class SimpleLetter extends Letter<Text> {

	public SimpleLetter(Inhabitant sender, Inhabitant receiver) {
		this(sender, receiver, "");
	}

	public SimpleLetter(Inhabitant sender, Inhabitant receiver, String content) {
		super(sender, receiver, new Text(content));
	}
	
	public SimpleLetter(Inhabitant sender, Inhabitant receiver, Text content) {
		super(sender, receiver, content);
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

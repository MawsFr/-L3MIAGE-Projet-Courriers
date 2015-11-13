package model.content.letter;

import model.Inhabitant;
import model.content.Text;

public class SimpleLetter extends Letter<Text> {

	public SimpleLetter(Inhabitant sender, Inhabitant receiver, String content, int cost) {
		super(sender, receiver, new Text(content), 1);
	}
	
	@Override
	public void doAction() {
		super.doAction();
		
	}
	
	
	
}

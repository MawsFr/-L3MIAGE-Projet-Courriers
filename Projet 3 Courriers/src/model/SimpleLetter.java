package model;

public class SimpleLetter extends Letter<Text> {

	public SimpleLetter(Inhabitant sender, Inhabitant receiver, Content content, double cost) {
		super(sender, receiver, content, 1);
	}
	
	@Override
	public void doAction() {
		super.doAction();
		
	}
	
	
	
}

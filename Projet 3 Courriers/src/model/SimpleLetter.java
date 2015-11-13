package model;

public class SimpleLetter extends Letter<Text> {

	public SimpleLetter(Inhabitant sender, Inhabitant receiver, String content, double cost) {
		super(sender, receiver, new Text(content), 1);
	}
	
	@Override
	public void doAction() {
		super.doAction();
		
	}
	
	
	
}

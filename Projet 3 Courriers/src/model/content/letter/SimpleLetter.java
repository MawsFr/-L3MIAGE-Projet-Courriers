package model.content.letter;

import model.city.Inhabitant;
import model.content.Text;

public class SimpleLetter extends Letter<Text> {

	/**
	 * Constructor with the sender, the receiver and a empty content
	 * @param sender The sender of this letter
	 * @param receiver The receiver of this letter 
	 */	
	public SimpleLetter(Inhabitant sender, Inhabitant receiver) {
		this(sender, receiver, "");
	}

	/**
	 * Constructor with the sender, the receiver and the content of this simple letter
	 * @param sender The sender of this simple letter
	 * @param receiver The receiver of this simple letter 
	 * @param content The content of this simple letter 
	 */	
	public SimpleLetter(Inhabitant sender, Inhabitant receiver, String content) {
		super(sender, receiver, new Text(content));
	}
	
	/**
	 * Constructor with the sender, the receiver and the content of this simple letter 
	 * @param sender The sender of this simple letter
	 * @param receiver The receiver of this simple letter 
	 * @param content The content of this simple letter 
	 */	
	public SimpleLetter(Inhabitant sender, Inhabitant receiver, Text content) {
		super(sender, receiver, content);
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#doAction()
	 */
	@Override
	public void doAction() {
		
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#getCost()
	 */
	@Override
	public double getCost() {
		return 1;
	}
	
	/* (non-Javadoc)
	 * @see model.content.letter.Letter#toString()
	 */
	@Override
	public String toString() {
		return "a simple "  + super.toString();
	}
}

package model.content;

/**
 * This class represents a text content of a letter
 * 
 * @see Content
 */
public class Text implements Content {
	
	/**
	 * The text content
	 */
	protected String content;
	
	/**
	 * Constructor with a string
	 * @param content The text content
	 */
	public Text(String content) {
		this.content = content;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "a text content (" + this.content + ")";
	}

}

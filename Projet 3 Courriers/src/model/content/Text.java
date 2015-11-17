package model.content;

public class Text implements Content {
	
	protected String content;
	
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

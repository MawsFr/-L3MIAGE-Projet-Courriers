package model.content;

public class Text implements Content {
	
	protected String content;
	
	public Text(String content) {
		this.content = content;
	}
	
	@Override
	public String getContent() {
		return this.content;
	}

}

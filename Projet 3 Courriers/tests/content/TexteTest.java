package content;

import model.content.Text;

import org.junit.Test;

/**
 * This class tests the Text class 
 */
public class TexteTest {

	/**
	 * Tests creating a Text with a null content
	 */
	@Test(expected=NullPointerException.class)
	public void nullContentTest() {
		new Text(null);
	}
	
	/**
	 * Tests creating a Text with a emtpy content
	 * Doesn't throws exceptions
	 */
	@Test
	public void emptyContentTest() {
		new Text("");
	}
}

package data;

import java.util.ArrayList;
import java.util.List;

public class Tokens {

	private List<Token> tokens;
	private String text;

	public Tokens() {
		tokens = new ArrayList<Token>();
		text = "";
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void add(Token tok) {
		tokens.add(tok);
		text += tok.getText().trim() + " ";
	}

	public TextType getType() {
		boolean blnNum = false;
		for (Token tok : tokens) {
			if (tok.getType() == TextType.NUMBER)
				blnNum = true;
			else {
				blnNum = false;
				break;
			}
		}
		return blnNum ? TextType.NUMBER : TextType.ALPHANUMERIC;
	}

	public boolean containsText(String text) {
		// boolean blnFound = false;
		// for (Token tok : tokens)
		// if (tok.getText().equalsIgnoreCase(text)) {
		// blnFound = true;
		// break;
		// }
		return getTextString().equalsIgnoreCase(text);
	}

	public String getTextString() {
		return text.trim();
	}

}

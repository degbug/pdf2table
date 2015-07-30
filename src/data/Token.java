package data;

import util.TypeIdentifier;

public class Token {

	private Location location;
	private Font font;
	private String text;
	private TextType type;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		type = TypeIdentifier.identifyType(text);
	}

	public TextType getType() {
		return type;
	}

	public void print() {
		location.print();
		font.print();
		System.out.println(text);
	}

	public void textPrint() {
		System.out.print(text + " ");
	}

}

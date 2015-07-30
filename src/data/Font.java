package data;

public class Font {

	private float size;
	private String color;
	private boolean italic;
	private boolean bold;
	private boolean serif;
	private boolean symbolic;
	private String name;

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isSerif() {
		return serif;
	}

	public void setSerif(boolean serif) {
		this.serif = serif;
	}

	public boolean isSymbolic() {
		return symbolic;
	}

	public void setSymbolic(boolean symbolic) {
		this.symbolic = symbolic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println("Size : " + size + " color : " + color
				+ " Italic : " + italic + "Bold : " + bold + "Serif: " + serif
				+ "Symbolic: " + symbolic + "Name : " + name);
	}

}

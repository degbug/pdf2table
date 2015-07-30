package data;

public class InvoicePage {

	private Location location;
	private Texts texts;

	public InvoicePage() {
		texts = new Texts();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void addText(Text text) {
		texts.addText(text);
	}

	public Texts getTexts() {
		return texts;
	}

	public void sortTexts() {
		texts.sort();
	}

	public Texts findText(String text) {
		return texts.findText(text);
	}

	public void inferNeighborhood() {
		texts.findNeighborhood();
	}

	public void print() {
		System.out.println("Height: " + getLocation().getHeight());
		System.out.println("Width: " + getLocation().getWidth());
		System.out.println("Number of texts : " + texts.size());
		System.out.println("Fist text : ");
		texts.get(0).print();
	}
}

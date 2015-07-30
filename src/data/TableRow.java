package data;

public class TableRow {

	private Texts rowTexts;
	private Location location;

	public TableRow() {
		rowTexts = new Texts();
		location = new Location();
	}

	public void addRowText(Text t) {
		rowTexts.addText(t);
		if (rowTexts.size() == 1)
			location = t.getLocation().copy();
		else {
			location.setY(Math.min(t.getLocation().getY(), location.getY()));
			float tY2 = t.getLocation().getY() + t.getLocation().getHeight();
			float locY2 = location.getY() + location.getHeight();
			float Y2 = Math.max(tY2, locY2);
			location.setHeight(Y2 - location.getY());
		}
	}

	public Texts getRowTexts() {
		return rowTexts;
	}

	public Location getLocation() {
		return location;
	}

	public boolean overlapsWith(Text t) {
		float tY1 = t.getLocation().getY();
		float tY2 = tY1 + t.getLocation().getHeight();
		float rowY1 = location.getY();
		float rowY2 = rowY1 + location.getHeight();
		return (tY1 <= rowY1 && rowY1 <= tY2) || (rowY1 <= tY1 && tY1 <= rowY2);
	}

	public int size() {
		return rowTexts.size();
	}

	public boolean containsText(Text text) {
		return rowTexts.getTexts().contains(text);
	}

	public void printDelimited() {
		StringBuilder strBuilder = new StringBuilder();
		for (Text t : rowTexts.getTexts())
			strBuilder.append(t.getTextString()).append('|');
		System.out.println(strBuilder.substring(0, strBuilder.length() - 1));
	}

}

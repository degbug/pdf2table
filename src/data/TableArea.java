package data;

import util.PDF2TableConstants;

public class TableArea {

	private Location location;
	private Texts tableTexts;
	private float avgInterCellHeight;
	private float minInterCellHeight;
	private float maxInterCellHeight;

	public TableArea() {
		tableTexts = new Texts();
		location = new Location();
		minInterCellHeight = 10000.0f;
		maxInterCellHeight = 0.0f;
		avgInterCellHeight = 0.0f;
	}

	public void addText(Text text) {
		tableTexts.addText(text);
		adjustLocation(text);
		computeMetrics(text);
	}

	private void computeMetrics(Text text) {
		Text dn = text.getDownNeighbor();
		if (tableTexts.getTexts().contains(dn)) {
			float h = dn.getLocation().getY() - text.getLocation().getY();
			if (h < minInterCellHeight)
				minInterCellHeight = h;
			if (h > maxInterCellHeight)
				maxInterCellHeight = h;
			avgInterCellHeight = (minInterCellHeight + maxInterCellHeight) / 2;
		}
//		System.out.println("Min: " + minInterCellHeight + ", Max: "
//				+ maxInterCellHeight + ", Avg: " + avgInterCellHeight);
	}

	public Texts getTableTexts() {
		return tableTexts;
	}

	private void adjustLocation(Text text) {
		Location tLocation = text.getLocation();
		if (tableTexts.size() == 1)
			location = text.getLocation().copy();
		else {
			float locX1 = location.getX();
			float locX2 = locX1 + location.getWidth();
			float locY1 = location.getY();
			float locY2 = locY1 + location.getHeight();
			float tX1 = tLocation.getX();
			float tX2 = tX1 + tLocation.getWidth();
			float tY1 = tLocation.getY();
			float tY2 = tY1 + tLocation.getWidth();
			location.setX(Math.min(locX1, tX1));
			location.setY(Math.min(locY1, tY1));
			float newLocX2 = Math.max(locX2, tX2);
			float newLocY2 = Math.max(locY2, tY2);
			location.setWidth(newLocX2 - location.getX());
			location.setHeight(newLocY2 - location.getY());
		}
	}

	public boolean isEmpty() {
		return tableTexts.size() == 0;
	}

	public boolean isOverlappingTexts(Text examineText) {
		boolean blnOverlap = false;
		Texts overlappingTexts = new Texts();
		for (Text t : tableTexts.getTexts()) {
			if (t.verticallyOverlapsWith(examineText)
					&& !overlappingTexts.verticallyOverlapsWith(t))
				overlappingTexts.addText(t);
		}
		for (int i = 0; i < overlappingTexts.size() - 1; i++) {
			Text outer = overlappingTexts.get(i);
			for (int j = i + 1; j < overlappingTexts.size(); j++) {
				Text inner = overlappingTexts.get(j);
				if (outer.horizontallyOverlapsWith(inner)
						&& !outer.verticallyOverlapsWith(inner)) {
					blnOverlap = true;
					break;
				}
			}
		}
		return blnOverlap;
	}

	public boolean contains(Text test) {
		return tableTexts.getTexts().contains(test);
	}

	public void print() {
		for (Text t : tableTexts.getTexts())
			t.textPrint();
	}

	public boolean isMeetsCellHeight(Text examineText) {
		Text dn = examineText.getDownNeighbor();
		boolean blnMeets = avgInterCellHeight == 0.0 || dn == null
				|| !tableTexts.getTexts().contains(dn) ? true : false;
		// if (dn != null) {
		// System.out.println("Down : ");
		// dn.textPrint();
		// }
		if (!blnMeets && dn != null && tableTexts.getTexts().contains(dn)) {
			float h = dn.getLocation().getY()
					- examineText.getLocation().getY();
			if (h >= (1 - PDF2TableConstants.HIEGHT_WINDOW)
					* avgInterCellHeight
					&& h <= (1 + PDF2TableConstants.HIEGHT_WINDOW)
							* avgInterCellHeight)
				blnMeets = true;
		}
		return blnMeets;
	}

	public void sort() {
		tableTexts.sort();
	}

	public Text findText(String str) {
		Texts found = tableTexts.findText(str);
		return found != null ? found.getFirst() : null;
	}
}

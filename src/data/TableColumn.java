package data;

import util.PDF2TableConstants;

public class TableColumn {

	private Texts columnTexts;
	private Location location;
	private Alignment alignment;
	private TextType type;

	public TableColumn() {
		columnTexts = new Texts();
		location = new Location();
	}

	public Location getLocation() {
		return location;
	}

	public void addText(Text text) {
		columnTexts.addText(text);
		if (columnTexts.size() == 1)
			location = text.getLocation().copy();
		else {
			float locX2 = location.getX() + location.getWidth();
			float tX2 = text.getLocation().getX()
					+ text.getLocation().getWidth();
			float x2 = Math.max(locX2, tX2);
			location.setX(Math.min(location.getX(), text.getLocation().getX()));
			location.setWidth(x2 - location.getX());
		}
		if (type == null
				|| (type == TextType.ALPHANUMERIC && text.getType() == TextType.NUMBER))
			type = text.getType();
	}

	public TextType getType() {
		return type;
	}

	public Texts getColumnTexts() {
		return columnTexts;
	}

	public void print() {
		for (Text t : columnTexts.getTexts())
			t.textPrint();
	}

	public int size() {
		return columnTexts.size();
	}

	public boolean contains(Text text) {
		return columnTexts.getTexts().contains(text);
	}

	public boolean isAlignsWith(Text text) {
		boolean blnLeft = isLeftAlignsWith(text);
		boolean blnRight = isRightAlignsWith(text);
		boolean blnCenter = isCenterAlignsWith(text);
		if ((blnLeft && blnRight) || (blnLeft && blnCenter)
				|| (blnRight && blnCenter))
			alignment = Alignment.UNSURE;
		return blnLeft || blnRight || blnCenter;
	}

	private boolean isLeftAlignsWith(Text text) {
		boolean blnAligned = false;
		if (alignment == null || alignment == Alignment.LEFT
				|| alignment == Alignment.UNSURE) {
			float cx = this.location.getX();
			float tx = text.getLocation().getX();
			if (Math.abs(cx - tx) < PDF2TableConstants.ALIGNMENT_THRESHOLD) {
				blnAligned = true;
				alignment = Alignment.LEFT;
			}
		}
		return blnAligned;
	}

	private boolean isRightAlignsWith(Text text) {
		boolean blnAligned = false;
		if (alignment == null || alignment == Alignment.RIGHT
				|| alignment == Alignment.UNSURE) {
			float cx = this.location.getX() + this.location.getWidth();
			float tx = text.getLocation().getX()
					+ text.getLocation().getWidth();
			if (Math.abs(cx - tx) < PDF2TableConstants.ALIGNMENT_THRESHOLD) {
				blnAligned = true;
				alignment = Alignment.RIGHT;
			}
		}
		return blnAligned;
	}

	private boolean isCenterAlignsWith(Text text) {
		boolean blnAligned = false;
		if (alignment == null || alignment == Alignment.CENTER
				|| alignment == Alignment.UNSURE) {
			float cx = this.location.getX() + this.location.getWidth() / 2;
			float tx = text.getLocation().getX()
					+ text.getLocation().getWidth() / 2;
			if (Math.abs(cx - tx) < PDF2TableConstants.ALIGNMENT_THRESHOLD) {
				blnAligned = true;
				alignment = Alignment.CENTER;
			}
		}
		return blnAligned;
	}

}

package data;

import util.MathUtil;

public class Text {

	private Location location;
	private Tokens tokens;
	private TextType type;

	private Text rightNeighbor;
	private Text leftNeighbor;
	private Text upNeighbor;
	private Text downNeighbor;

	public Text() {
		tokens = new Tokens();
	}

	public TextType getType() {
		return type;
	}

	public void setType() {
		type = tokens.getType();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void addToken(Token tok) {
		tokens.add(tok);
	}

	public Text getRightNeighbor() {
		return rightNeighbor;
	}

	public void setRightNeighbor(Text rightNeighbor) {
		this.rightNeighbor = rightNeighbor;
	}

	public Text getLeftNeighbor() {
		return leftNeighbor;
	}

	public void setLeftNeighbor(Text leftNeighbor) {
		this.leftNeighbor = leftNeighbor;
	}

	public Text getUpNeighbor() {
		return upNeighbor;
	}

	public void setUpNeighbor(Text upNeighbor) {
		this.upNeighbor = upNeighbor;
	}

	public Text getDownNeighbor() {
		return downNeighbor;
	}

	public void setDownNeighbor(Text downNeighbor) {
		this.downNeighbor = downNeighbor;
	}

	public void print() {
		shortPrint();
		System.out.println("Left Neigh: ");
		if (leftNeighbor != null)
			leftNeighbor.shortPrint();
		System.out.println("Right Neigh: ");
		if (rightNeighbor != null)
			rightNeighbor.shortPrint();
		System.out.println("Top Neigh: ");
		if (upNeighbor != null)
			upNeighbor.shortPrint();
		System.out.println("Down Neigh: ");
		if (downNeighbor != null)
			downNeighbor.shortPrint();
	}

	public void shortPrint() {
		System.out.println("Location : ");
		location.print();
		System.out.println("Tokens : ");
		for (Token tok : tokens.getTokens())
			tok.print();
		System.out.println("Type: " + type);
	}

	public void textPrint() {
		for (Token tok : tokens.getTokens())
			tok.textPrint();
		System.out.println();
	}

	public boolean containsText(String text) {
		return tokens.containsText(text);
	}

	public float vertEuclidDist(Text another) {
		float[] thisCoord = new float[] {
				this.location.getX() + this.location.getWidth() / 2,
				this.location.getY() + this.location.getHeight() / 2 };
		float[] anotherCoord = new float[] {
				another.location.getX() + another.location.getWidth() / 2,
				another.location.getY() + another.location.getHeight() / 2 };
		return MathUtil.euclidDist(thisCoord, anotherCoord);
	}

	public boolean overlapsWith(Text examineText) {
		return verticallyOverlapsWith(examineText)
				|| horizontallyOverlapsWith(examineText);
	}

	public boolean verticallyOverlapsWith(Text examineText) {
		boolean blnOverlap = false;
		float thisX1 = location.getX();
		float thisX2 = thisX1 + location.getWidth();
		float thatX1 = examineText.getLocation().getX();
		float thatX2 = thatX1 + examineText.getLocation().getWidth();

		if ((thisX1 <= thatX1 && thatX1 <= thisX2)
				|| (thatX1 <= thisX1 && thisX1 <= thatX2))
			blnOverlap = true;
		return blnOverlap;
	}

	public boolean horizontallyOverlapsWith(Text examineText) {
		boolean blnOverlap = false;
		float thisY1 = location.getY();
		float thisY2 = thisY1 + location.getHeight();
		float thatY1 = examineText.getLocation().getY();
		float thatY2 = thatY1 + examineText.getLocation().getHeight();
		if ((thisY1 <= thatY1 && thatY1 <= thisY2)
				|| (thatY1 <= thisY1 && thisY1 <= thatY2))
			blnOverlap = true;
		return blnOverlap;
	}

	public String getTextString() {
		return tokens.getTextString();
	}

}

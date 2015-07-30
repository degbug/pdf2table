package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Texts {

	private List<Text> texts;

	public Texts() {
		texts = new ArrayList<Text>();
	}

	public List<Text> getTexts() {
		return texts;
	}

	public void addText(Text text) {
		texts.add(text);
	}

	public int size() {
		return texts.size();
	}

	public Text get(int i) {
		return texts.get(i);
	}

	public void sort() {
		Collections.sort(texts, new Comparator<Text>() {

			@Override
			public int compare(Text t1, Text t2) {
				return t1.getLocation().getY() < t2.getLocation().getY() ? -1
						: (t1.getLocation().getY() > t2.getLocation().getY() ? 1
								: t1.getLocation().getX() < t2.getLocation()
										.getX() ? -1 : 1);
			}
		});
	}

	public Texts findText(String text) {
		Texts foundTexts = new Texts();
		for (Text t : texts)
			if (t.containsText(text))
				foundTexts.addText(t);
		return foundTexts;
	}

	public void findNeighborhood() {
		for (int i = 1; i < texts.size(); i++) {
			float dist = 10000.0f;
			Text topNeigh = null;
			float checkDist = 0.0f;
			Text whoseNeigh = texts.get(i);
			Text leftNeigh = texts.get(i - 1);
			if (whoseNeigh.getLocation().getX() > leftNeigh.getLocation()
					.getX() + leftNeigh.getLocation().getWidth()) {
				whoseNeigh.setLeftNeighbor(leftNeigh);
				leftNeigh.setRightNeighbor(whoseNeigh);
			}
			for (int j = i - 1; j >= 0 && j >= i - 11; j--) {
				Text checkNeigh = texts.get(j);
				if (checkNeigh.getLocation().getY() < whoseNeigh.getLocation()
						.getY()
						&& checkNeigh.verticallyOverlapsWith(whoseNeigh)) {
					// checkDist = whoseNeigh.vertEuclidDist(checkNeigh);
					// if (checkDist < dist) {
					topNeigh = checkNeigh;
					// dist = checkDist;
					break;
					// }
				}
			}
			if (topNeigh != null) {
				whoseNeigh.setUpNeighbor(topNeigh);
				topNeigh.setDownNeighbor(whoseNeigh);
			}
		}
	}

	public boolean verticallyOverlapsWith(Text test) {
		boolean flag = false;
		for (Text t : texts) {
			if (t.verticallyOverlapsWith(test)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean overlapsWith(Text test) {
		boolean flag = false;
		for (Text t : texts) {
			if (t.overlapsWith(test)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public Text getLast() {
		return texts != null && texts.size() > 0 ? texts.get(texts.size() - 1)
				: null;
	}

	public Text before(Text thisText, Text thatText) {
		return texts.indexOf(thisText) < texts.indexOf(thatText) ? thisText
				: thatText;
	}

	public Text getFirst() {
		return texts != null && texts.size() > 0 ? texts.get(0) : null;
	}
}

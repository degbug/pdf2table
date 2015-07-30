package pdf2table;

import java.util.LinkedList;
import java.util.Queue;

import data.InvoicePage;
import data.TableArea;
import data.Text;
import data.Texts;

public class TableAreaScanner {
	private static final String[] TABLE_SCAN_WORDS = { "Total", "Subtotal",
			"Total Discount" };

	private InvoicePage iPage;
	private TableArea tableArea;

	public TableAreaScanner(InvoicePage page) {
		iPage = page;
		tableArea = new TableArea();
	}

	public Text findScanStart(Texts texts) {
		Text found = null;
		Text thisText = null;
		for (String word : TABLE_SCAN_WORDS) {
			thisText = texts.findText(word).getLast();
			if (thisText != null)
				found = found == null ? thisText : texts
						.before(thisText, found);
		}
		return found;
	}

	public TableArea scan() {
		Queue<Text> scanQ = new LinkedList<Text>();
		Texts textBoxes = iPage.getTexts();
		// Search for text box containing text "Total"
		Text totalText = findScanStart(textBoxes);
		if (totalText == null)
			totalText = iPage.getTexts().getLast();
		scanQ.add(totalText.getUpNeighbor());
		Text toAdd = null;
		while (!scanQ.isEmpty()) {
			Text examineText = scanQ.remove();
			if (checkAdd(examineText)) {
				tableArea.addText(examineText);
				toAdd = examineText.getLeftNeighbor();
				if (toAdd != null && !scanQ.contains(toAdd)
						&& !tableArea.contains(toAdd)) {
					// System.out.println("Adding ");
					// examineText.getLeftNeighbor().textPrint();
					scanQ.add(examineText.getLeftNeighbor());
				}
				toAdd = examineText.getRightNeighbor();
				if (toAdd != null && !scanQ.contains(toAdd)
						&& !tableArea.contains(toAdd)) {
					// System.out.println("Adding ");
					// examineText.getRightNeighbor().textPrint();
					scanQ.add(examineText.getRightNeighbor());
				}
				toAdd = examineText.getUpNeighbor();
				if (toAdd != null && !scanQ.contains(toAdd)
						&& !tableArea.contains(examineText.getUpNeighbor())) {
					// System.out.println("Adding ");
					// examineText.getUpNeighbor().textPrint();
					scanQ.add(examineText.getUpNeighbor());
				}
			}
		}
		tableArea.sort();
		return tableArea;
	}

	private boolean checkAdd(Text examineText) {
//		System.out.println("Checking for textbox...");
//		examineText.textPrint();
		boolean blnCheck = tableArea.isEmpty()
				|| (!tableArea.isOverlappingTexts(examineText) && tableArea
						.isMeetsCellHeight(examineText));
//		System.out.println(blnCheck);
		return blnCheck;
	}

}

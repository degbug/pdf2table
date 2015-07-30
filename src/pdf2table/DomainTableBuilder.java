package pdf2table;

import data.ItemEntry;
import data.ItemTable;
import data.TableArea;
import data.TableColumn;
import data.TableRow;
import data.TableRows;
import data.Text;

public class DomainTableBuilder extends TableBuilder {
	// private TableColumn descriptionColumn;
	// private TableColumn qtyColumn;
	// private TableColumn priceColumn;
	// private TableColumn amountColumn;

	public DomainTableBuilder(TableArea area) {
		super(area);
	}

	public ItemTable buildTable() {
		TableColumn descColumn = buildColumn(ITEM_DESCRIPTION);
		TableColumn qtyColumn = buildColumn(ITEM_QUANTITY);
		TableColumn priceColumn = buildColumn(ITEM_PRICE);
		TableColumn amountColumn = buildColumn(ITEM_AMOUNT);

		TableRows tableRows = new TableRows();
		for (Text amount : amountColumn.getColumnTexts().getTexts()) {
			TableRow row = new TableRow();
			row.addRowText(amount);
			tableRows.addRow(row);
		}
		alignRows(descColumn, tableRows);
		alignRows(priceColumn, tableRows);
		alignRows(qtyColumn, tableRows);

		tableRows = tableRows.removeSparse();

		ItemTable itemsTable = new ItemTable();
		String textStr = null;
		for (TableRow row : tableRows.getRows()) {
			ItemEntry item = new ItemEntry();
			itemsTable.add(item);
			for (Text text : row.getRowTexts().getTexts()) {
				textStr = text.getTextString();
				if (descColumn.contains(text))
					item.setName(textStr);
				if (qtyColumn.contains(text))
					item.setQty(textStr);
				if (priceColumn.contains(text))
					item.setPrice(textStr);
				if (amountColumn.contains(text))
					item.setAmount(textStr);
			}
		}
		itemsTable.print();
		return null;
	}

	private void alignRows(TableColumn column, TableRows rows) {
		TableRow row = null;
		for (Text t : column.getColumnTexts().getTexts()) {
			row = rows.getRowOverlapsWith(t);
			if (row != null)
				row.addRowText(t);
		}
	}

	public TableColumn buildColumn(String[] titles) {
		Text titleText = null;
		for (String title : titles) {
			titleText = tableArea.findText(title);
			if (titleText != null)
				break;
		}
		// System.out.println("Title: " + titleText.getTextString());
		TableColumn column = new TableColumn();
		if (titleText != null) {
			Text neighbor = titleText.getDownNeighbor();
			while (neighbor != null && tableArea.contains(neighbor)) {
				// System.out.println("Checking " + neighbor.getTextString());
				column.addText(neighbor);
				neighbor = neighbor.getDownNeighbor();
			}
		}
		return column;
	}

}

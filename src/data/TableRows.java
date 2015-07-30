package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.PDF2TableConstants;

public class TableRows {

	private List<TableRow> rows;

	public TableRows() {
		rows = new ArrayList<TableRow>();
	}

	public void addRow(TableRow row) {
		rows.add(row);
	}

	public List<TableRow> getRows() {
		return rows;
	}

	public TableRow getRowOverlapsWith(Text t) {
		TableRow overlappingRow = null;
		for (TableRow row : rows)
			if (row.overlapsWith(t)) {
				overlappingRow = row;
				break;
			}
		return overlappingRow;
	}

	public TableRows removeSparse() {
		TableRows denseRows = new TableRows();
		for (TableRow row : rows)
			if (row.size() >= PDF2TableConstants.SIZE_MINTHRESHOLD)
				denseRows.addRow(row);
		return denseRows;
	}

	public void placeText(Text text) {
		boolean blnAdded = false;
		for (TableRow row : rows) {
			if (row.overlapsWith(text)) {
				row.addRowText(text);
				blnAdded = true;
				break;
			}
		}
		if (!blnAdded) {
			TableRow row = new TableRow();
			row.addRowText(text);
			rows.add(row);
		}
	}

}

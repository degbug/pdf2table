package data;

import java.util.ArrayList;
import java.util.List;

import util.PDF2TableConstants;

public class TableColumns {

	private List<TableColumn> columns;

	public TableColumns() {
		columns = new ArrayList<TableColumn>();
	}

	public void addColumn(TableColumn column) {
		columns.add(column);
	}

	public List<TableColumn> getColumns() {
		return columns;
	}

	public void placeText(Text text) {
		boolean blnAdded = false;
		for (TableColumn column : columns) {
			if (column.isAlignsWith(text)) {
				column.addText(text);
				blnAdded = true;
				break;
			}
		}
		if (!blnAdded) {
			TableColumn col = new TableColumn();
			col.addText(text);
			columns.add(col);
		}
	}

	public TableColumns removeSparse() {
		TableColumns denseColumns = new TableColumns();
		for (TableColumn col : columns) {
			if (col.size() >= PDF2TableConstants.COL_SIZE_MINTHRESHOLD)
				denseColumns.addColumn(col);
		}
		return denseColumns;
	}

	public void print() {
		for (TableColumn col : columns) {
			System.out.println("Printing column...");
			col.print();
		}
	}

}

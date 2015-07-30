package pdf2table;

import data.ItemTable;
import data.TableArea;

public abstract class TableBuilder {
	protected static final String[] ITEM_DESCRIPTION = { "Item name",
			"Description" };
	protected static final String[] ITEM_QUANTITY = { "Quantity", "Qty" };
	protected static final String[] ITEM_PRICE = { "Price", "Unit Price" };
	protected static final String[] ITEM_AMOUNT = { "Amount", "Line total" };

	protected TableArea tableArea;

	public TableBuilder(TableArea area) {
		tableArea = area;
	}

	public abstract ItemTable buildTable();

}

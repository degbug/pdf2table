package pdf2table;

import data.ItemTable;
import data.TableArea;
import data.TableColumn;
import data.TableColumns;
import data.TableRow;
import data.TableRows;
import data.Text;
import data.TextType;

public class AlignmentTableBuilder extends TableBuilder {

	public AlignmentTableBuilder(TableArea area) {
		super(area);
	}

	@Override
	public ItemTable buildTable() {
		TableColumns tColumns = new TableColumns();
		TableRows tRows = new TableRows();
		for (Text text : tableArea.getTableTexts().getTexts()) {
			tColumns.placeText(text);
			tRows.placeText(text);
		}
//		tColumns.print();
		tColumns = tColumns.removeSparse();		

		float maxWidth = 0.0f;
		TableColumn descColumn = null;
		for (TableColumn col : tColumns.getColumns()) {
//			System.out.println(col.getType());
			if (col.getType() == TextType.ALPHANUMERIC
					&& col.getLocation().getWidth() > maxWidth) {
				maxWidth = col.getLocation().getWidth();
				descColumn = col;
			}
		}
		for (Text text : descColumn.getColumnTexts().getTexts()) {
			for (TableRow row : tRows.getRows()) {
				if (row.containsText(text))
					row.printDelimited();
			}
		}
		// tColumns.print();
		return null;
	}
}

package data;

import java.util.ArrayList;
import java.util.List;

public class ItemTable {

	private List<ItemEntry> itemsList;

	public ItemTable() {
		itemsList = new ArrayList<ItemEntry>();
	}

	public List<ItemEntry> getItemsList() {
		return itemsList;
	}

	public void add(ItemEntry item) {
		itemsList.add(item);
	}

	public void print() {
		for (ItemEntry item : itemsList)
			System.out.println(item.asDelimited());
	}

}

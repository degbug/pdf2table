package data;

public class ItemEntry {

	private String name;
	private String qty;
	private String price;
	private String amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String asDelimited() {
		StringBuilder strBuilder = new StringBuilder();
		if (name != null)
			strBuilder.append(name).append('|');
		if (qty != null)
			strBuilder.append(qty).append('|');
		if (price != null)
			strBuilder.append(price).append('|');
		if (amount != null)
			strBuilder.append(amount);
		return strBuilder.toString();
	}

}

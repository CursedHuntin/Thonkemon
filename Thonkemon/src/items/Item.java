package items;

import monsters.Monster;

public abstract class Item {
	public String name;
	public int amount;
	public int price;

	public Item(String name, int amount, int price) {
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public abstract void effect(Monster m);

}

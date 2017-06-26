package items;

import monsters.Monster;

public abstract class Item {
	public String name;
	public int amount;

	public Item(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public abstract void effect(Monster m);

}

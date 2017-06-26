package items.potions;

import items.Item;
import monsters.Monster;

public abstract class Potion extends Item {

	public Potion(String name, int amount) {
		super(name, amount);
	}

	public abstract void effect(Monster m);
}

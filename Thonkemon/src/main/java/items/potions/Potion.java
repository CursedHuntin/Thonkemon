package main.java.items.potions;

import main.java.items.Item;
import main.java.monsters.Monster;

public abstract class Potion extends Item {

	public Potion(String name, int amount, int price) {
		super(name, amount, price);
	}

	public abstract void effect(Monster m);
}

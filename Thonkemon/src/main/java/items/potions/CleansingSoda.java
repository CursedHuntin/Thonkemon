package main.java.items.potions;

import main.java.monsters.Monster;

public class CleansingSoda extends Potion {

	public CleansingSoda(int amount) {
		super("Cleansing Soda", amount, 1000);
	}

	@Override
	public void effect(Monster m) {
		m.restoreStats();
	}

}

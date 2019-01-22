package main.java.items.misc;

import main.java.items.Item;
import main.java.monsters.Monster;

public class RareCandy extends Item {

	public RareCandy(int amount) {
		super("Rare Candy", amount, -1);
	}

	@Override
	public void effect(Monster m) {
		m.exp.exp += (m.exp.nextLevel - m.exp.exp);
		m.levelUp();
	}

}

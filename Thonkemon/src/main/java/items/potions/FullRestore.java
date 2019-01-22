package main.java.items.potions;

import main.java.monsters.Monster;

public class FullRestore extends Potion {

	public FullRestore(int amount) {
		super("Full Restore", amount, 2000);
	}

	@Override
	public void effect(Monster m) {
		if ((m.stats.hp == m.stats.maxHP) && (m.status != null)) {
			m.stats.hp = m.stats.maxHP;
			m.status = null;
			System.out.println(m.name + " has been healed!");
		} else
			System.out.println("This would have no effect!");
	}

}

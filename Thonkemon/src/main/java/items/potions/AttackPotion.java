package main.java.items.potions;

import main.java.monsters.Monster;

public class AttackPotion extends Potion {

	public AttackPotion(int amount) {
		super("Attack Potion", amount, 1000);
	}

	@Override
	public void effect(Monster m) {
		if (amount > 0) {
			m.stats.atk *= 1.1;
			System.out.println(m.name + "'s attack has been raised!");
			amount--;
		}
	}

}

package main.java.items.potions;

import main.java.monsters.Monster;

public class MediumHealthPotion extends Potion {

	private static final long serialVersionUID = -5860111078274486734L;

	public MediumHealthPotion(int amount) {
		super("Medium Health Potion", amount, 500);
	}

	@Override
	public void effect(Monster m) {
		if (amount > 0) {
			if (m.stats.hp < m.stats.maxHP / 2) {
				m.stats.hp = (m.stats.hp + m.stats.maxHP / 2);
				System.out.println("Healed for " + m.stats.maxHP / 2 + " HP!");
			} else if (m.stats.hp == m.stats.maxHP) {
				System.out.println("This would have no effect!");
				return;
			} else {
				System.out.println("Healed for " + (m.stats.maxHP - m.stats.hp) + " HP!");
				m.stats.hp = (m.stats.maxHP);
			}
			amount--;
		}
	}

}

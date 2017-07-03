package items.potions;

import monsters.Monster;

public class MediumHealthPotion extends Potion {

	public MediumHealthPotion(int amount) {
		super("MediumHealthPotion", amount, 500);
	}

	@Override
	public void effect(Monster m) {
		if (m.stats.getHp() < m.stats.getMaxHp() / 2) {
			m.stats.setHp(m.stats.getHp() + m.stats.getMaxHp() / 2);
			System.out.println("Healed for " + m.stats.getMaxHp() / 2 + " HP!");
		} else {
			System.out.println("Healed for " + (m.stats.getMaxHp() - m.stats.getHp()) + " HP!");
			m.stats.setHp(m.stats.getMaxHp());
		}
	}

}

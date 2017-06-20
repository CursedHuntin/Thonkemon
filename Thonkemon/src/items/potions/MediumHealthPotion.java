package items.potions;

import monsters.Monster;

public class MediumHealthPotion extends Potion {

	@Override
	void effect(Monster m) {
		m.stats.setHp(m.stats.getHp() + m.stats.getMaxHp() / 2);
	}

}

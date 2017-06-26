package statuses;

import monsters.Monster;

public class Poison extends Status {

	private int turns;
	private int chance = 25;

	public Poison(Monster m) {
		super("Poison");
	}

	public void effect(Monster m) {
		poisoned(m);
	}

	public void applyEffect(Monster m) {
		if (chance > Math.random() * 100) {
			turns = (int) (Math.random() * 5) + 1;
			m.status = this;
		}
	}

	// untested
	private void poisoned(Monster m) {
		if (turns > 0) {
			System.out.println(m.name + " is poisoned!");
			System.out.println(m.name + " lost " + (int) (m.stats.getMaxHp() * 0.1) + " HP!");
			m.stats.setHp((int) (m.stats.getHp() - (m.stats.getMaxHp() * 0.1)));
			turns--;
		} else {
			System.out.println(m.name + " is no longer poisoned!");
			m.status = null;
		}
	}
}

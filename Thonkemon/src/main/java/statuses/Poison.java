package main.java.statuses;

import main.java.monsters.Monster;

public class Poison extends Status {

	private static final long serialVersionUID = 9216889234587703937L;
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
			System.out.println(m.name + " lost " + (int) (m.stats.maxHP * 0.1) + " HP!");
			m.stats.hp = ((int) (m.stats.hp - (m.stats.maxHP * 0.1)));
			turns--;
		} else {
			System.out.println(m.name + " is no longer poisoned!");
			m.status = null;
		}
	}
}

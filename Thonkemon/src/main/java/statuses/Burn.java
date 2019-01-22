package main.java.statuses;

import main.java.monsters.Monster;

public class Burn extends Status {

	private static final long serialVersionUID = -1828086115103334854L;
	private int turns;
	private int chance = 25;

	public Burn() {
		super("Burn");
	}

	public void effect(Monster m) {
		burn(m);
	}

	public void applyEffect(Monster m) {
		if (chance > Math.random() * 100) {
			turns = (int) (Math.random() * 5) + 1;
			m.status = this;
		}
	}

	// untested
	private void burn(Monster m) {
		if (turns > 0) {
			System.out.println(m.name + " is burning!");
			System.out.println(m.name + " lost " + (int) (m.stats.maxHP * 0.1) + " HP!");
			m.stats.hp = ((int) (m.stats.hp - (m.stats.maxHP * 0.1)));
			turns--;
		} else {
			System.out.println(m.name + " is no longer burning!");
			m.status = null;
		}
	}

}

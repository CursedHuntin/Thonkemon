package main.java.statuses;

import main.java.game.fight.Fight;
import main.java.monsters.Monster;

public class Paralyze extends Status {

	private int turns;
	private int chance = 33;

	public Paralyze(Monster m) {
		super("Paralyze");
	}

	public void effect(Monster m) {
		paralyze(m);
	}

	public void applyEffect(Monster m) {
		if (chance > Math.random() * 100) {
			turns = (int) (Math.random() * 5) + 1;
			m.status = this;
		}
	}

	private void paralyze(Monster m) {
		if (turns > 0) {
			if (50 > Math.random() * 100) {
				System.out.println(m.name + " is paralyzed!");
				Fight.skipTurn = true;
			}
			turns--;
		} else {
			System.out.println(m.name + " is no longer paralyzed!");
			m.status = null;
		}

	}
}

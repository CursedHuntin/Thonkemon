package main.java.statuses;

import main.java.game.fight.Fight;
import main.java.monsters.Monster;

public class Scared extends Status {

	public Scared(Monster m) {
		super("Scared");
	}

	public void effect(Monster m) {
		scared(m);
	}

	private void scared(Monster m) {
		System.out.println(m.name + " got scared!");
		Fight.skipTurn = true;
		m.status = null;
	}

	public void applyEffect(Monster m) {
		m.status = this;
	}
}

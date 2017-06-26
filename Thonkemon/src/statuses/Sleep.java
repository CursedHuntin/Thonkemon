package statuses;

import game.fight.Fight;
import monsters.Monster;

public class Sleep extends Status {

	int turns;
	private int chance = 25;

	public Sleep(Monster m) {
		super("Sleep");
	}

	public void effect(Monster m) {
		sleep(m);
	}

	public void applyEffect(Monster m) {
		if (chance > Math.random() * 100) {
			turns = (int) (Math.random() * 3) + 1;
			m.status = this;
		}
	}

	// untested
	private void sleep(Monster m) {
		if (turns > 0) {
			System.out.println(m.name + " is asleep!");
			Fight.skipTurn = true;
			turns--;
		} else {
			System.out.println(m.name + " is no longer asleep!");
			m.status = null;
		}
	}
}

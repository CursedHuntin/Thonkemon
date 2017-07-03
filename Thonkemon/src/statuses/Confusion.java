package statuses;

import game.fight.Fight;
import monsters.Monster;

public class Confusion extends Status {

	private int turns;
	private int chance = 50;

	public Confusion(Monster m) {
		super("Confusion");
	}

	public void effect(Monster m) {
		confusion(m);
	}

	public void applyEffect(Monster m) {
		if (chance > Math.random() * 100) {
			turns = (int) (Math.random() * 3) + 1;
			m.status = this;
		}
	}

	// untested
	private void confusion(Monster m) {
		if (turns > 0) {
			System.out.println(m.name + " is confused!");
			if (50 < (Math.random() * 100)) {
				System.out.println(m.name + " hurt itself!");
				m.stats.setHp((int) (m.stats.getHp() * 0.9));
				Fight.skipTurn = true;
			}
			turns--;
		} else {
			System.out.println(m.name + " is no longer confused!");
			m.status = null;
		}
	}
}

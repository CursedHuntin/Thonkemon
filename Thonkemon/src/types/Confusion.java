package types;

import game.Fight;
import game.Status;
import monsters.Monster;

public class Confusion extends Status {

	private int turns;
	private Monster m;

	public Confusion(Monster m) {
		super("Confusion");
		this.m = m;
		turns = (int) (Math.random() * 3 + 1);
	}

	public void effect() {

	}

	private void Confusion() {
		if (turns > 0) {
			System.out.println(m.name + " is confused!");
			if (50 < (Math.random() * 100)) {
				System.out.println(m.name + " hurt itself!");
				m.stats.setHp((int) (m.stats.getHp() * 0.9));
				Fight.skipTurn = true;
			}
			turns--;
		} else
			m.status = null;
	}
}

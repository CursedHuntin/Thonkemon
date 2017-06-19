package types;

import game.Status;
import monsters.Monster;

public class Poison extends Status {

	private Monster m;
	private int turns;

	public Poison(Monster m) {
		super("Poison");
		this.m = m;
		turns = (int) (Math.random() * 9 + 1);
		m.status = this;
	}

	public void effect() {
		poisoned();
	}

	private void poisoned() {
		if (turns > 0) {
			System.out.println(m.name + " is poisoned!");
			System.out.println(m.name + " lost " + (int) (m.stats.getHp() - (m.stats.getHp() * 0.9)) + " HP!");
			m.stats.setHp((int) (m.stats.getHp() * 0.9));
			turns--;
		} else
			m.status = null;
	}

	public int getTurns() {
		return turns;
	}
}

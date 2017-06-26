package types;

import monsters.Monster;
import statuses.Status;

public class Healing extends Status {
	private int heal;
	private Monster m;

	public Healing(int heal, Monster m) {
		super("Healing");
		this.heal = heal;
		this.m = m;
	}

	public void effect(Monster m) {
	}

	void heal(Monster m) {
		if (m.stats.getHp() + heal < m.stats.getMaxHp()) {
			m.stats.setHp(m.stats.getHp() + heal);
			System.out.println(m.name + " healed for " + heal + " HP");
		} else {
			System.out.println(m.name + " healed for " + (m.stats.getMaxHp() - m.stats.getHp()) + " HP");
			m.stats.setHp(m.stats.getMaxHp());
		}
	}

	public void applyEffect(Monster m) {
		heal(this.m);
	}

}

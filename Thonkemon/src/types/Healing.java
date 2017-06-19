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
		System.out.println("heal()");
		m.stats.setHp(m.stats.getHp() + heal);
		System.out.println("You healed for " + heal + " HP");
	}

	public void applyEffect(Monster m) {
		heal(this.m);
	}

}

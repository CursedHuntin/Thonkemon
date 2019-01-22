package main.java.statuses;

import main.java.monsters.Monster;

public class Healing extends Status {

	private static final long serialVersionUID = -6635264149767434965L;
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
		if (m.stats.hp + heal < m.stats.maxHP) {
			m.stats.hp = (m.stats.hp + heal);
			System.out.println(m.name + " healed for " + heal + " HP");
		} else {
			System.out.println(m.name + " healed for " + (m.stats.maxHP - m.stats.hp) + " HP");
			m.stats.hp = (m.stats.maxHP);
		}
	}

	public void applyEffect(Monster m) {
		heal(this.m);
	}

}

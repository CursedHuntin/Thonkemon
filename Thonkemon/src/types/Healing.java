package types;

import monsters.Monster;

public class Healing extends AbilityStatus {
	int heal;
	Monster m;

	public Healing(int heal, Monster m) {
		super("Healing");
		this.heal = heal;
		this.m = m;
	}

	public void effect() {
		heal();
	}

	void heal() {
		m.stats.setHp(m.stats.getHp() + heal);
		System.out.println("You healed for " + heal + " HP");
	}

}

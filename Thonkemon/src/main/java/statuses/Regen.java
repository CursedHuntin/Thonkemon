package main.java.statuses;

import main.java.monsters.Monster;

public class Regen extends Status {

	private static final long serialVersionUID = -8488362939117327492L;

	public Regen() {
		super("Heal");
	}

	@Override
	public void effect(Monster m) {
		heal(m);
	}

	@Override
	public void applyEffect(Monster m) {
		m.status = this;
	}

	private void heal(Monster m) {
		m.stats.hp = (m.stats.hp + (m.stats.maxHP / 10));
	}

}

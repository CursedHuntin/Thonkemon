package statuses;

import monsters.Monster;

public class Regen extends Status {

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
		m.stats.setHp(m.stats.getHp() + (m.stats.getMaxHp() / 10));
	}

}

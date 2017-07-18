package game.fight;

import monsters.Monster;

public class Leveling {

	public Leveling(Monster m1, Monster m2) {
		awardExp(m1, m2);
	}

	public void levelUp(Monster m) {
		m.stats.setExp(m.stats.getExp() - (int) (100 * Math.pow(1.1, m.stats.getLevel() - 1)));
		m.stats.setLevel(m.stats.getLevel() + 1);
	}

	public boolean isLevelUp(Monster m) {
		if (m.stats.getExp() >= 100 * (int) Math.pow(1.1, m.stats.getLevel() - 1)) {
			System.out.println(m.name + " just got to level " + m.stats.getLevel());
			return true;
		} else
			return false;
	}

	public void awardExp(Monster m1, Monster m2) {
		System.out.println(m1.name + " got " + m2.stats.getKillExp() + " EXP!");
		m1.stats.setExp(m2.stats.getKillExp());
		while (isLevelUp(m1) && m1.stats.getLevel() < 100)
			levelUp(m1);
	}
}

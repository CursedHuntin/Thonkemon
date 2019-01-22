package main.java.moves;

import main.java.monsters.Monster;
import main.java.statuses.Healing;
import main.java.statuses.StatusAttack;
import main.java.types.Normal;

public class Heal extends Move {

	private static final long serialVersionUID = -271537499348351092L;

	public Heal(int level, Monster m) {
		super("Heal", new Normal(), new StatusAttack(), level, 0, 10, 100,
				new Healing((int) (10 + m.stats.spAtk / 1.5), m));
	}
}

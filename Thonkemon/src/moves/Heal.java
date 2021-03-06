package moves;

import monsters.Monster;
import statuses.StatusAttack;
import types.Healing;
import types.Normal;

public class Heal extends Move {

	public Heal(int level, Monster m) {
		super("Heal", new Normal(), new StatusAttack(), level, 0, 10, 100,
				new Healing((int) (10 + m.stats.getSpAtk() / 1.5), m));
	}
}

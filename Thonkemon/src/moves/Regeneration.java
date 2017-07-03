package moves;

import statuses.Regen;
import statuses.StatusAttack;
import types.Normal;

public class Regeneration extends Move {

	public Regeneration(int level) {
		super("Regeneration", new Normal(), new StatusAttack(), level, 0, 5, 100, new Regen());
	}

}

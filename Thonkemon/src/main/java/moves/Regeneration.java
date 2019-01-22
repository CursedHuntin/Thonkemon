package main.java.moves;

import main.java.statuses.Regen;
import main.java.statuses.StatusAttack;
import main.java.types.Normal;

public class Regeneration extends Move {

	public Regeneration(int level) {
		super("Regeneration", new Normal(), new StatusAttack(), level, 0, 5, 100, new Regen());
	}

}

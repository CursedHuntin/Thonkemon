package main.java.moves;

import main.java.statuses.StatusAttack;
import main.java.types.Fire;

public class MoltenShield extends Move {

	public MoltenShield(int level) {
		// Needs OnHit
		// Basically Thornmail / MoltenShield hehe xd
		super("Molten Shield", new Fire(), new StatusAttack(), level, 0, 5, 100, null);
	}

}

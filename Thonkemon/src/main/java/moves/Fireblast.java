package main.java.moves;

import main.java.statuses.Burn;
import main.java.types.Fire;
import main.java.types.Magical;

public class Fireblast extends Move {

	public Fireblast(int level) {
		super("Fireblast", new Fire(), new Magical(), level, 60, 15, 80, new Burn());
	}

}

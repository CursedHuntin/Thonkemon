package main.java.moves;

import main.java.statuses.StatusAttack;
import main.java.types.Normal;

public class Cleanse extends Move {

	public Cleanse(int level) {
		super("Cleanse", new Normal(), new StatusAttack(), level, 0, 10, 100, new main.java.statuses.Cleanse());
	}

}

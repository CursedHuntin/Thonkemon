package main.java.moves;

import main.java.types.Fighting;
import main.java.types.Physical;

public class Chop extends Move {

	public Chop(int level) {
		super("Chop", new Fighting(), new Physical(), level, 40, 20, 80, null);
	}
}

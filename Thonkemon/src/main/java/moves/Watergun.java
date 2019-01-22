package main.java.moves;

import main.java.types.Magical;
import main.java.types.Water;

public class Watergun extends Move {

	public Watergun(int level) {
		super("Watergun", new Water(), new Magical(), level, 50, 15, 80, null);
	}

}

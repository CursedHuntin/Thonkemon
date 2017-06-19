package moves;

import monsters.Water;
import types.Magical;

public class Watergun extends Move {

	public Watergun(int level) {
		super("Watergun", new Water(), new Magical(), level, 50, 15, 80, null);
	}

}

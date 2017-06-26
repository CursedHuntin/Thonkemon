package moves;

import types.Magical;
import types.Water;

public class Watergun extends Move {

	public Watergun(int level) {
		super("Watergun", new Water(), new Magical(), level, 50, 15, 80, null);
	}

}

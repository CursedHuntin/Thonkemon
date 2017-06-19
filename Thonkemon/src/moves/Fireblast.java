package moves;

import statuses.Burn;
import types.Magical;

public class Fireblast extends Move {

	public Fireblast(int level) {
		super("Fireblast", new Fire(), new Magical(), level, 60, 15, 80, new Burn());
	}

}

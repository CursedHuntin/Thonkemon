
package moves;

import types.Normal;
import types.Physical;

public class Tackle extends Move {

	public Tackle(int level) {
		super("Tackle", new Normal(), new Physical(), level, 30, 20, 80, null);
	}

}

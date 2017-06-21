package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Heal;
import moves.Move;
import moves.Tackle;
import moves.Watergun;
import types.Water;

public class Wotah extends Monster {

	public Wotah(int level) {
		// name, type1, type2, level, hp, atk, def, spatk, spdef, init, catch
		super("Wotah", new Water(), null, level, 19, 7, 6, 8, 7, 8, 60);
		super.moveset = getMoveset();
		super.moves = getMoves(level);
	}

	List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Watergun(5));
		m.add(new Heal(10, this));
		return m;
	}

	List<Move> getMoves(int level) {
		for (Move move : super.moveset) {
			if (level >= move.level)
				moves.add(move);
		}
		return moves;
	}
}

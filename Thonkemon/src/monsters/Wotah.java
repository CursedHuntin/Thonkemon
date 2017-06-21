package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Heal;
import moves.Move;
import moves.Tackle;
import moves.Watergun;
import types.Type;
import types.Water;

public class Wotah extends Monster {
	public static String name = "Wotah";
	public static Type[] types = { new Water(), null };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 19, 7, 6, 8, 7, 8, 60 };

	public Wotah(int level) {
		super(name, types, level, stats);
		super.moveset = getMoveset();
		super.moves = getMoves(level);
	}

	public List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Watergun(5));
		m.add(new Heal(10, this));
		return m;
	}

	public List<Move> getMoves(int level) {
		for (Move move : super.moveset) {
			if (level >= move.level)
				moves.add(move);
		}
		return moves;
	}
}

package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Chop;
import moves.Fireblast;
import moves.Heal;
import moves.Move;
import moves.Tackle;
import types.Fighting;
import types.None;
import types.Type;

public class Krato extends Monster {

	public static String name = "Krato";
	public static Type[] types = { new Fighting(), new None() };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 25, 10, 5, 1, 3, 7, 80 };

	public Krato(int level) {
		super(name, types, level, stats);
		super.moveset = getMoveset();
		super.moves = getMoves(level);

	}

	public List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Chop(7));
		m.add(new Heal(10, this));
		m.add(new Fireblast(15));
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

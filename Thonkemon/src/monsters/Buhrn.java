package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Fire;
import moves.Fireblast;
import moves.Move;
import moves.Tackle;
import types.Type;

public class Buhrn extends Monster {

	public static String name = "Buhrn";
	public static Type[] types = { new Fire(), null };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 22, 2, 5, 10, 6, 10, 60 };

	public Buhrn(int level) {
		super(name, types, level, stats);
		super.moveset = getMoveset();
		super.moves = getMoves(level);
	}

	public List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Fireblast(7));
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

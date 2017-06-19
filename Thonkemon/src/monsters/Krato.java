package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Chop;
import moves.Fireblast;
import moves.Heal;
import moves.Move;
import moves.Tackle;
import types.Fighting;

public class Krato extends Monster {

	public Krato(int level) {
		// name, type1, type2, level, hp, atk, def, spatk, spdef, init, catch

		super("Krato", new Fighting(), null, level, 25, 10, 5, 1, 3, 7, 80);
		super.moveset = getMoveset();
		super.moves = getMoves(level);

	}

	List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Chop(7));
		m.add(new Heal(10, this));
		m.add(new Fireblast(15));
		return m;
	}

	List<Move> getMoves(int level) {
		for (Move move : super.moveset) {
			if (level >= move.level)
				moves.add(move);
		}
		return moves;
	}

	void setNickname(String name) {
		this.name = name;
	}

}

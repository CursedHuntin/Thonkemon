package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Fire;
import moves.Fireblast;
import moves.Move;
import moves.Tackle;

public class Buhrn extends Monster {

	public Buhrn(int level) {
		// name, type1, type2, level, hp, atk, def, spatk, spdef, init, catch

		super("Buhrn", new Fire(), null, level, 22, 2, 5, 10, 6, 10, 60);
		super.moveset = getMoveset();
		super.moves = getMoves(level);
	}

	@Override
	List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Fireblast(7));
		return m;
	}

	@Override
	List<Move> getMoves(int level) {
		for (Move move : super.moveset) {
			if (level >= move.level)
				moves.add(move);
		}
		return moves;
	}

	@Override
	void setNickname(String name) {
	}

}

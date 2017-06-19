package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Chop;
import moves.Heal;
import moves.Move;
import moves.Tackle;
import types.Fighting;
import types.Type;

public class Krato extends Monster {
	static String name = "Krato";
	static Type type1 = new Fighting();
	static Type type2 = null;
	static int hp = 25;
	static int atk = 10;
	static int def = 5;
	static int spAtk = 1;
	static int spDef = 3;
	static int init = 7;
	static int catchrate = 80;

	public Krato(int level) {
		super(name, type1, type2, level, hp, atk, def, spAtk, spDef, init, catchrate);
		super.moveset = getMoveset();
		super.moves = getMoves(level);

	}

	List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Chop(7));
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

	void setNickname(String name) {
		Krato.name = name;
	}

}

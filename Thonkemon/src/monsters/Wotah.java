package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Chop;
import moves.Heal;
import moves.Move;
import moves.Tackle;
import moves.Watergun;
import types.Type;

public class Wotah extends Monster {
	static String name = "Wotah";
	static Type type1 = new Water();
	static Type type2 = null;
	static int hp = 1;
	static int atk = 7;
	static int def = 6;
	static int spAtk = 8;
	static int spDef = 7;
	static int init = 8;
	static int catchrate = 60;

	public Wotah(int level) {
		super(name, type1, type2, level, hp, atk, def, spAtk, spDef, init, catchrate);
		super.moveset = getMoveset();
		super.moves = getMoves(level);
	}

	List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Watergun(5));
		m.add(new Heal(10, this));
		m.add(new Chop(7));
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
		Wotah.name = name;
	}
}

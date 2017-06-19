package monsters;

import java.util.ArrayList;
import java.util.List;

import game.Status;
import moves.Move;
import types.Type;

public class Monster {
	public String name;
	public List<Move> moveset = new ArrayList<Move>();
	public List<Move> moves = new ArrayList<Move>();
	public Stats stats;
	public Status status;

	public Monster(String name, Type type1, Type type2, int level, int maxHP, int atk, int def, int spAtk, int spDef,
			int init, int catchrate) {

		this.name = name;
		stats = new Stats(type1, type2, level, maxHP, atk, def, spAtk, spDef, init, catchrate);

	}

	List<Move> getMoveset() {
		return null;
	}

	List<Move> getMoves(int level) {
		return null;
	}
}

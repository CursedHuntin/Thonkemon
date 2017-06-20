package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Move;
import statuses.Status;
import types.Type;

public abstract class Monster {
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

	abstract List<Move> getMoveset();

	abstract List<Move> getMoves(int level);

	void setNickname(String name) {
		this.name = name;
	}
}

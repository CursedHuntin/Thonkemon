package monsters;

import java.util.ArrayList;
import java.util.List;

import game.StdIn;
import moves.Move;
import statuses.Status;
import types.Type;

public abstract class Monster {
	public String name;
	public List<Move> moveset = new ArrayList<Move>();
	public List<Move> moves = new ArrayList<Move>();
	public Stats stats;
	public int[] initStats;
	public Status status;
	public Type[] types;
	public static Move[] MoveArray = new Move[4];

	public Monster(String name, Type[] types, int level, int[] stats) {
		this.name = name;
		this.initStats = stats;
		this.types = types;
		this.stats = new Stats(types, level, stats);
	}

	abstract List<Move> getMoveset();

	abstract List<Move> getMoves(int level);

	public void setNickname(String name) {
		this.name = name;
	}

	public static void setMoves(Monster m) {
		System.out.println("Select your Moves: ");
		for (Move move : m.moves) {
			System.out.print(move.name + " ");
		}
		int i = 0;
		boolean k;
		while (i < 4 && i < m.moves.size()) {
			k = false;
			String s = StdIn.readString();
			for (Move move : m.moves) {
				if (move.name.equalsIgnoreCase(s)) {
					for (int j = 0; j < 4; j++) {
						if (MoveArray[j] != null) {
							if (MoveArray[j].name.equalsIgnoreCase(s)) {
								k = true;
							}
						}
					}
					if (!k) {
						MoveArray[i] = move;
						i++;
					}
				}

			}
		}

		List<Move> fightMoves = new ArrayList<Move>();
		int a = 0;
		boolean b;

		while (a < 4 && a < m.moves.size()) {
			b = false;
			String s = StdIn.readString();
			for (Move move : m.moves) {
				if (move.name.equalsIgnoreCase(s)) {
					for (Move x : fightMoves) {
						if (x.name.equalsIgnoreCase(s))
							b = true;
					}
					if (!b) {
						fightMoves.add(move);
						a++;
					}
				}
			}
		}
	}
}

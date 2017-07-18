package monsters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.StdIn;
import moves.Move;
import statuses.Status;
import types.Type;

public abstract class Monster implements Serializable {
	private static final long serialVersionUID = -4967621942093163953L;
	public String name;
	public List<Move> moveset = new ArrayList<Move>();
	public List<Move> moves = new ArrayList<Move>();
	public Stats stats;
	public int[] initStats;
	public Status status;
	public Type[] types;
	public List<Move> fightMoves = new ArrayList<Move>();
	public int exp;

	public Monster(String name, Type[] types, int level, int[] stats) {
		this.name = name;
		this.initStats = stats;
		this.types = types;
		this.stats = new Stats(types, level, stats);
	}

	abstract List<Move> getMoveset();

	void getMoves(int level) {
		for (Move move : moveset) {
			if (level >= move.level)
				moves.add(move);
		}
		// setMoves();
		Collections.reverse(moves);
		int i = 0;
		for (Move m : moves) {
			if (i == 4)
				break;
			fightMoves.add(m);
			i++;

		}
		Collections.reverse(moves);
	}

	public void setNickname(String name) {
		this.name = name;
	}

	public List<Move> setMoves() {
		System.out.println("Select your Moves: ");
		for (Move move : this.moves) {
			System.out.print(move.name + " ");
		}

		List<Move> fightMoves = new ArrayList<Move>();
		int a = 0;
		boolean b;

		while (a < 4 && a < this.moves.size()) {
			b = false;
			String s = StdIn.readString();
			for (Move move : this.moves) {
				if (move.name.equalsIgnoreCase(s)) {
					for (Move x : fightMoves) {
						if (x.name.equalsIgnoreCase(s))
							b = true;
					}
					if (!b) {
						fightMoves.add(move);
						a++;
						System.out.println(move.name + " has been added!");
					}
				}
			}
		}
		System.out.println(this.name + "'s new active Moves are: ");
		for (Move mv : fightMoves) {
			System.out.print(mv.name + " ");
		}
		System.out.println("");
		return fightMoves;
	}

	public abstract void changeLevel(int level);
}

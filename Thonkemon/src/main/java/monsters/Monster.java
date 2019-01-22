package main.java.monsters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.game.StdIn;
import main.java.moves.Move;
import main.java.stats.Experience;
import main.java.stats.Stats;
import main.java.statuses.Status;
import main.java.types.Type;

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
	public Experience exp;

	public Monster(String name, Type[] types, int level, int[] stats) {
		this.name = name;
		this.initStats = stats;
		this.types = types;
		this.stats = new Stats(level, stats);
		exp = new Experience(level, 0);
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

	public void levelUp() {
		while (exp.exp >= exp.nextLevel) {
			if (exp.level < 100) {
				exp.level++;
				exp.exp -= exp.nextLevel;
				exp.nextLevel *= (int) 1.1;
			} else
				exp.exp = 0;
		}
	}

	public void applyEXP(Monster m) {
		if (exp.level < 100) {
			exp.exp += m.exp.defeatEXP;
			if (exp.exp >= exp.nextLevel)
				levelUp();
		}
	}

	public void restoreStats() {
		int hp = stats.hp;
		stats = new Stats(exp.level, initStats);
		stats.hp = hp;
	}
}

package main.java.monsters;

import java.util.ArrayList;
import java.util.List;

import main.java.moves.Chop;
import main.java.moves.Fireblast;
import main.java.moves.Heal;
import main.java.moves.Move;
import main.java.moves.Tackle;
import main.java.types.Fighting;
import main.java.types.None;
import main.java.types.Type;

public class Krato extends Monster {

	public static String name = "Krato";
	public static Type[] types = { new Fighting(), new None() };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 25, 10, 5, 1, 3, 7, 80, 75 };

	public Krato(int level) {
		super(name, types, level, stats);
		moveset = getMoveset();
		getMoves(level);

	}

	public List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Chop(7));
		m.add(new Heal(10, this));
		m.add(new Fireblast(15));
		return m;
	}
}

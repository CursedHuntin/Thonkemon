package main.java.monsters;

import java.util.ArrayList;
import java.util.List;

import main.java.moves.Fireblast;
import main.java.moves.Move;
import main.java.moves.Tackle;
import main.java.types.Fire;
import main.java.types.None;
import main.java.types.Type;

public class Buhrn extends Monster {

	public static String name = "Buhrn";
	public static Type[] types = { new Fire(), new None() };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 22, 2, 5, 10, 6, 10, 60, 80 };

	public Buhrn(int level) {
		super(name, types, level, stats);
		moveset = getMoveset();
		getMoves(level);
	}

	public List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Fireblast(7));
		return m;
	}
}

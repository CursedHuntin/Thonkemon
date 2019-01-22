package main.java.monsters;

import java.util.ArrayList;
import java.util.List;

import main.java.moves.Heal;
import main.java.moves.Move;
import main.java.moves.Tackle;
import main.java.moves.Watergun;
import main.java.types.None;
import main.java.types.Type;
import main.java.types.Water;

public class Wotah extends Monster {
	public static String name = "Wotah";
	public static Type[] types = { new Water(), new None() };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 19, 7, 6, 8, 7, 8, 60, 85 };

	public Wotah(int level) {
		super(name, types, level, stats);
		moveset = getMoveset();
		getMoves(level);
	}

	public List<Move> getMoveset() {
		List<Move> m = new ArrayList<Move>();
		m.add(new Tackle(1));
		m.add(new Watergun(5));
		m.add(new Heal(10, this));
		return m;
	}
}

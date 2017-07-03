package monsters;

import java.util.ArrayList;
import java.util.List;

import moves.Fire;
import moves.Fireblast;
import moves.Move;
import moves.Tackle;
import types.None;
import types.Type;

public class Buhrn extends Monster {

	public static String name = "Buhrn";
	public static Type[] types = { new Fire(), new None() };
	// hp, atk, def, spatk, spdef, init, catchrate
	public static int[] stats = { 22, 2, 5, 10, 6, 10, 60 };

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

	@Override
	public void changeLevel(int level) {
		super.stats = new Stats(types, level, stats);
	}
}

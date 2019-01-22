package main.java.stats;

import java.io.Serializable;

public class Stats implements Serializable {
	private static final long serialVersionUID = -481507815548710437L;
	public int maxHP;
	public int hp;
	public int atk;
	public int def;
	public int spAtk;
	public int spDef;
	public double init;
	public int catchrate;

	public Stats(int level, int[] stats) {
		maxHP = (int) (stats[0] + ((stats[0] / 1.5) * (level / 5)));
		atk = (stats[1] + (stats[1] * (level / 5)));
		def = (stats[2] + (stats[2] * (level / 5)));
		spAtk = (stats[3] + (stats[3] * (level / 5)));
		spDef = (stats[4] + (stats[4] * (level / 5)));
		init = (stats[5] + ((stats[5] / 5) * ((level / 5) + 1)));
		catchrate = (stats[6]);
		hp = (this.maxHP);
	}
}

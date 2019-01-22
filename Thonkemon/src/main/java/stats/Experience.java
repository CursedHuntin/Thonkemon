package main.java.stats;

import java.io.Serializable;

public class Experience implements Serializable {

	private static final long serialVersionUID = 2484727565402786265L;
	public int level;
	public int exp;
	public int nextLevel;
	public int defeatEXP;

	public Experience(int level, int exp) {
		this.level = level;
		this.exp = exp;
		nextLevel = (int) (100 * (Math.pow(1.1, level - 1)));
		defeatEXP = level * 20;
	}
}

package monsters;

import types.Type;

public class Stats {

	private Type type1;
	private Type type2;
	private int level;
	private int maxHP;
	private int hp;
	private int atk;
	private int def;
	private int spAtk;
	private int spDef;
	private double init;
	private int catchrate;

	public Stats(Type type1, Type type2, int level, int maxHP, int atk, int def, int spAtk, int spDef, double init,
			int catchrate) {

		setType1(type1);
		setType2(type2);
		setLevel(level);
		setMaxHp((int) (maxHP + ((maxHP / 1.5) * (level / 5))));
		setAtk(atk + (atk * (level / 5)));
		setDef(def + (def * (level / 5)));
		setSpAtk(spAtk + (spAtk * (level / 5)));
		setSpDef(spDef + (spDef * (level / 5)));
		setInit(init + ((init / 5) * ((level / 5) + 1)));
		setCatchrate(catchrate);
		setHp(this.maxHP);

	}

	public Type getType1() {
		return type1;
	}

	public void setType1(Type type1) {
		this.type1 = type1;
	}

	public Type getType2() {
		return type2;
	}

	public void setType2(Type type2) {
		this.type2 = type2;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaxHp() {
		return hp;
	}

	public void setMaxHp(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpAtk() {
		return spAtk;
	}

	public void setSpAtk(int spAtk) {
		this.spAtk = spAtk;
	}

	public int getSpDef() {
		return spDef;
	}

	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}

	public double getInit() {
		return init;
	}

	public void setInit(double init) {
		this.init = init;
	}

	public int getCatchrate() {
		return catchrate;
	}

	public void setCatchrate(int catchrate) {
		this.catchrate = catchrate;
	}

}

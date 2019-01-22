package main.java.moves;

import java.io.Serializable;

import main.java.statuses.Status;
import main.java.types.Type;

public class Move implements Serializable {
	private static final long serialVersionUID = -5033230559369105031L;
	public String name;
	public Type type;
	public Type damageType;
	public int level;
	public int damage;
	public int abilitypoints;
	public int accuracy;
	public Status status;

	public Move(String name, Type type, Type damageType, int level, int damage, int abilitypoints, int accuracy,
			Status status) {
		this.name = name;
		this.type = type;
		this.damageType = damageType;
		this.level = level;
		this.damage = damage;
		this.abilitypoints = abilitypoints;
		this.accuracy = accuracy;
		this.status = status;

	}

}

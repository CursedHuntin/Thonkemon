package moves;

import statuses.Status;
import types.Type;

public class Move {

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

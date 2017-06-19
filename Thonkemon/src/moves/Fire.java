package moves;

import types.Type;

public class Fire extends Type {

	public Fire() {
		super("Fire");
		addWeakness();
		addStrength();
	}

	void addWeakness() {
		super.weakness.add("Water");
		super.weakness.add("Rock");

	}

	void addStrength() {
		super.strength.add("Plant");
		super.strength.add("Ice");

	}
}

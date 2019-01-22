package main.java.types;

public class Fire extends Element {

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

	@Override
	void addImmunity() {
	}
}

package main.java.types;

public class Water extends Element {

	public Water() {
		super("Water");
		addWeakness();
		addStrength();
	}

	void addWeakness() {
		super.weakness.add("Plant");
		super.weakness.add("Electro");

	}

	void addStrength() {
		super.strength.add("Rock");
		super.strength.add("Fire");

	}

	@Override
	void addImmunity() {
	}
}

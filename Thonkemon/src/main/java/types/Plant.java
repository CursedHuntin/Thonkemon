package main.java.types;

public class Plant extends Element {

	public Plant() {
		super("Plant");
		addWeakness();
		addStrength();
	}

	void addWeakness() {
		super.weakness.add("Fire");
		super.weakness.add("Toxin");

	}

	void addStrength() {
		super.strength.add("Water");
		super.strength.add("Rock");

	}

	@Override
	void addImmunity() {
	}
}

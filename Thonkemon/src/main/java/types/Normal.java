package main.java.types;

public class Normal extends Element {

	public Normal() {
		super("Normal");
		addWeakness();
		addStrength();
	}

	void addWeakness() {
		super.weakness.add("Fighting");

	}

	void addStrength() {
		super.strength.add(null);

	}

	@Override
	void addImmunity() {
	}
}

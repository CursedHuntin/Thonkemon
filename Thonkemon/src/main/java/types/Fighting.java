package main.java.types;

public class Fighting extends Element {

	public Fighting() {
		super("Fighting");
		addWeakness();
		addStrength();

	}

	void addWeakness() {
		super.weakness.add(null);

	}

	void addStrength() {
		super.strength.add("Normal");

	}

	@Override
	void addImmunity() {
	}
}

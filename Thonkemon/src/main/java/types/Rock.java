package main.java.types;

public class Rock extends Element {

	public Rock(String name) {
		super(name);
	}

	@Override
	void addStrength() {
		super.strength.add("Fire");
	}

	@Override
	void addWeakness() {
		super.weakness.add("Water");
		super.weakness.add("Plant");
	}

	@Override
	void addImmunity() {
		super.immunity.add("Electro");
	}

}

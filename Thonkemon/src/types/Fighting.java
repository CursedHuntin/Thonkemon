package types;

public class Fighting extends Type {

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
}

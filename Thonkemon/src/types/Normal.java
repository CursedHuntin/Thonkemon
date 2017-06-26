package types;

public class Normal extends Type {
	public String[] weakness = { "Fighting" };
	public String[] strength = { "nothing" };

	public Normal() {
		super("Normal");
		addWeakness();
		addStrength();
	}

	private void addWeakness() {
		super.weakness.add("Fighting");

	}

	private void addStrength() {
		super.strength.add(null);

	}
}

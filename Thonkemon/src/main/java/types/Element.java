package main.java.types;

public abstract class Element extends Type {

	public Element(String name) {
		super(name);
	}

	abstract void addStrength();

	abstract void addWeakness();

	abstract void addImmunity();

}

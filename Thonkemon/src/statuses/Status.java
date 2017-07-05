package statuses;

import java.io.Serializable;

import monsters.Monster;

public abstract class Status implements Serializable {
	private static final long serialVersionUID = -8420937768820429876L;
	public String name;

	public Status(String name) {
		this.name = name;

	}

	public abstract void effect(Monster m);

	public abstract void applyEffect(Monster m);
}

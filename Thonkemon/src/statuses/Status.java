package statuses;

import monsters.Monster;

public abstract class Status {
	public String name;

	public Status(String name) {
		this.name = name;

	}

	public abstract void effect(Monster m);

	public abstract void applyEffect(Monster m);
}

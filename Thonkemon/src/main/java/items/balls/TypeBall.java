package main.java.items.balls;

import main.java.monsters.Monster;
import main.java.types.Type;

public class TypeBall extends Ball {

	public TypeBall(int amount) {
		super("Type Ball", 20, amount, 300);
	}

	@Override
	public boolean effect(Monster pm, Monster m) {
		if (amount > 0) {
			for (Type t : pm.types) {
				try {
					if (m.types[0].name.equals(t.name))
						this.catchrate *= 2;
					if (m.types[1].name.equals(t.name))
						this.catchrate *= 2;
				} catch (NullPointerException e) {

				}
			}
			amount--;
			return true;
		} else
			return false;
	}
}

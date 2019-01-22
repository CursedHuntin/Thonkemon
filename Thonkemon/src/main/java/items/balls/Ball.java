package main.java.items.balls;

import main.java.items.Item;
import main.java.monsters.Monster;

public class Ball extends Item {
	private static final long serialVersionUID = 1L;
	public int catchrate;

	public Ball(String name, int catchrate, int amount, int price) {
		super(name, amount, price);
		this.catchrate = catchrate;
	}

	@Override
	public void effect(Monster m) {
	}

	public boolean effect(Monster activeMonster, Monster m) {
		if (amount > 0) {
			amount--;
			return true;
		} else
			return false;
	}
}

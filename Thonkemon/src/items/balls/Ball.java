package items.balls;

import items.Item;
import monsters.Monster;

public class Ball extends Item {
	public int catchrate;

	public Ball(String name, int catchrate, int amount, int price) {
		super(name, amount, price);
		this.catchrate = catchrate;
	}

	@Override
	public void effect(Monster m) {
	}
}

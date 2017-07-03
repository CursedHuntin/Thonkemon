package game;

import items.balls.Ball;
import items.balls.BetterBall;
import items.balls.GoodBall;
import items.balls.StandardBall;
import items.balls.SuperBall;

public class Store {

	public Store() {
	}

	private void selection(Player p) {
		System.out.println("Select Type: ");
		System.err.println("Potions Balls");
		boolean k = false;
		while (!k) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("potions")) {
				potionStore(p);
				k = true;
			} else if (s.equalsIgnoreCase("balls")) {
				ballStore(p);
				k = true;
			}
		}
	}

	private void ballStore(Player p) {
		System.out.println("Standard Better Good Super");
		boolean k = false;
		while (!k) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("standard")) {
				buyBall(p, new StandardBall(0));
			} else if (s.equalsIgnoreCase("good")) {
				buyBall(p, new GoodBall(0));
			} else if (s.equalsIgnoreCase("better")) {
				buyBall(p, new BetterBall(0));
			} else if (s.equalsIgnoreCase("super")) {
				buyBall(p, new SuperBall(0));
			}
		}
	}

	private void buyBall(Player p, Ball ball) {
		System.out.println("Select amount: ");
		while (true) {
			String s = StdIn.readString();
			if (s.matches("[0-9]*")) {
				if (p.money >= Integer.parseInt(s)) {
					ball.amount = Integer.parseInt(s);
					p.money -= ball.amount;
					p.balls.add(ball);
				} else
					System.out.println("You don't have enough money!");
			}
		}
	}

	private void potionStore(Player p) {

	}

}

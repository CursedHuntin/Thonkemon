package game;

import items.Item;
import items.balls.Ball;
import items.balls.BetterBall;
import items.balls.GoodBall;
import items.balls.StandardBall;
import items.balls.SuperBall;
import items.potions.MediumHealthPotion;

public class Store {

	public Store(Player p) {
		selection(p);
	}

	private void selection(Player p) {
		System.out.println("Select Type: ");
		System.out.println("Potions Balls");
		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("potions")) {
				potionStore(p);
				break;
			} else if (s.equalsIgnoreCase("balls")) {
				ballStore(p);
				break;
			}
		}
	}

	private void ballStore(Player p) {
		System.out.println("Standard Better Good Super");
		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("standard")) {
				buyBall(p, new StandardBall(0));
				break;
			} else if (s.equalsIgnoreCase("good")) {
				buyBall(p, new GoodBall(0));
				break;
			} else if (s.equalsIgnoreCase("better")) {
				buyBall(p, new BetterBall(0));
				break;
			} else if (s.equalsIgnoreCase("super")) {
				buyBall(p, new SuperBall(0));
				break;
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
					break;
				} else {
					System.out.println("You don't have enough money!");
					break;
				}
			}
		}
	}

	private void potionStore(Player p) {
		System.out.println("Medium");
		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("medium")) {
				buyPotion(p, new MediumHealthPotion(0));
			}
		}
	}

	private void buyPotion(Player p, Item item) {
		System.out.println("Select amount: ");
		while (true) {
			String s = StdIn.readString();
			if (s.matches("[0-9]*")) {
				if (p.money >= Integer.parseInt(s)) {
					item.amount = Integer.parseInt(s);
					p.money -= item.amount;
					p.items.add(item);
					break;
				} else {
					System.out.println("You don't have enough money!");
					break;
				}
			}
		}
	}

}

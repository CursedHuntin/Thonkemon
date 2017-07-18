package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import game.fight.RandomEncounter;
import items.Item;
import items.balls.Ball;
import items.balls.MasterBall;
import items.potions.MediumHealthPotion;
import monsters.Buhrn;
import monsters.Krato;
import monsters.Monster;
import monsters.Wotah;

public class Player implements Serializable {
	private static final long serialVersionUID = 5007816868968013693L;
	public String name;
	public List<Monster> team = new ArrayList<Monster>();
	public List<Monster> box = new ArrayList<Monster>();
	public List<Item> items = new ArrayList<Item>();
	public List<Ball> balls = new ArrayList<Ball>();
	public int money = 1000;

	public Player() {
		this.name = getName();
		createTeam();
		createItems();
		createBalls();
	}

	public Player(String name) {
		this.name = name;
		createTeam();
		createItems();
	}

	public Player(String name, Monster m) {
		this.name = name;
		team.add(m);
		createItems();
	}

	private String getName() {
		System.out.println("Enter the Name of your Player: ");
		return StdIn.readString();
	}

	private void createTeam() {
		team.add(new Wotah(50));
		team.add(new Krato(50));
		team.add(new Buhrn(50));
	}

	private void createItems() {
		items.add(new MediumHealthPotion(1));
	}

	private void createBalls() {
		balls.add(new MasterBall(1));
	}

	public List<Monster> addMonsterToTeam(List<Monster> team, Monster m) {
		team.add(m);
		return team;
	}

	private void selectStarter() {
		System.out.println("Select your Starter: ");
		boolean k = false;
		while (!k) {
			String s = StdIn.readString();
			for (Monster m : RandomEncounter.createMonsters(1)) {
				if (m.name.equalsIgnoreCase(s)) {
					team.add(m);
					k = true;
				}
			}
			if (!k)
				System.out.println("Wrong Input!");
		}
	}

	void manageBox() {
		System.out.println("Select the Monster you want to deposit: ");
		for (Monster m : team) {
			System.out.print(m.name + " ");
		}
		System.out.println("Back");
		while (true) {
			String s = StdIn.readString();
			for (Monster m : team) {
				if (m.name.equalsIgnoreCase(s)) {
					box.add(m);
					team.remove(m);
					System.out.println(m.name + " has been deposited in your box!");
					for (Monster l : box) {
						System.out.println(l.name);
					}
					return;
				} else if (s.equalsIgnoreCase("back"))
					return;
			}
		}
	}
}

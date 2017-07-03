package game;

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

public class Player {
	public String name;
	public List<Monster> team = new ArrayList<Monster>();
	public List<Monster> box = new ArrayList<Monster>();
	public List<Item> items = new ArrayList<Item>();
	public List<Ball> balls = new ArrayList<Ball>();
	public int money = 0;

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

	private void manageBox() {

	}
}

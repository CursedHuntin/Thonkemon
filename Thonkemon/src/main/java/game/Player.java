package main.java.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.game.fight.RandomEncounter;
import main.java.items.Item;
import main.java.items.balls.Ball;
import main.java.items.balls.MasterBall;
import main.java.items.potions.MediumHealthPotion;
import main.java.monsters.Buhrn;
import main.java.monsters.Krato;
import main.java.monsters.Monster;
import main.java.monsters.Wotah;

public class Player implements Serializable {
	private static final long serialVersionUID = 5007816868968013693L;
	public String name;
	public List<Monster> team = new ArrayList<Monster>();
	public List<Monster> box = new ArrayList<Monster>();
	public List<Item> items = new ArrayList<Item>();
	public List<Ball> balls = new ArrayList<Ball>();
	public int money = 1000;
	public boolean isNPC;

	public Player() {
		this.name = getName();
		createTeam();
		createItems();
		createBalls();
		isNPC = false;
	}

	public Player(String name) {
		this.name = name;
		createTeam();
		createItems();
		createBalls();
		isNPC = false;
		box.add(new Wotah(69));

	}

	public Player(Monster m) {
		this.name = "Wild " + m.name;
		team.add(m);
		createItems();
		isNPC = true;
	}

	public Player createRandomPlayer() {
		int monsterCount = (int) (Math.random() * 6) + 1;
		Player p = new Player("Player 2");
		p.team.clear();
		for (int i = 0; i < monsterCount; i++) {
			int rng = (int) (Math.random() * RandomEncounter.createMonsters(0).size());
			int level = (int) (Math.random() * 100 + 1);
			p.team.add(RandomEncounter.createMonsters(level).get(rng));
		}
		return p;
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
		while (true) {
			String s = StdIn.readString();
			for (Monster m : RandomEncounter.createMonsters(1)) {
				if (m.name.equalsIgnoreCase(s)) {
					team.add(m);
					return;
				}
			}
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

	public Monster getActiveMonster() {
		return team.get(0);
	}

	public Monster getNextMonster() {
		for (Monster m : team) {
			if (m.stats.hp > 0)
				Collections.swap(team, 0, team.indexOf(m));
		}
		return getActiveMonster();
	}

	public void setActiveMonster(Monster m) {
		team.add(getActiveMonster());
		team.remove(m);
		team.set(0, m);
	}

	public void restoreStats() {
		for (Monster m : team) {
			m.restoreStats();
		}
	}
}

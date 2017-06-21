package game;

import java.util.ArrayList;
import java.util.List;

import items.Item;
import items.potions.MediumHealthPotion;
import monsters.Buhrn;
import monsters.Krato;
import monsters.Monster;
import monsters.Wotah;

public class Player {
	String name;
	List<Monster> team = new ArrayList<Monster>();
	List<Item> items = new ArrayList<Item>();

	public Player() {
		this.name = getName();
		createTeam();
		createItems();
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

	List<Monster> addMonsterToTeam(List<Monster> team, Monster m) {
		team.add(m);
		return team;
	}
}

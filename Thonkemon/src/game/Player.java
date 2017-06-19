package game;

import java.util.ArrayList;
import java.util.List;

import monsters.Krato;
import monsters.Monster;
import monsters.Wotah;

public class Player {
	String name;
	List<Monster> team;

	public Player() {
		this.name = getName();
		team = createTeam();
	}

	public Player(String name) {
		this.name = name;
		team = createTeam();
	}

	static String getName() {
		System.out.println("Enter the Name of your Player: ");
		return StdIn.readString();
	}

	static List<Monster> createTeam() {
		List<Monster> team = new ArrayList<Monster>();
		team.add(new Wotah(50));
		team.add(new Krato(50));
		return team;
	}

	List<Monster> addMonsterToTeam(List<Monster> team, Monster m) {
		team.add(m);
		return team;
	}
}

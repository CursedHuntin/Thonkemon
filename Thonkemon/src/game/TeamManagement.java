package game;

import java.util.Collections;

import monsters.Monster;

public class TeamManagement {

	public TeamManagement(Player p1, Player p2) {
		preFightManagement(selectPlayer(p1, p2));
		new Selection(p1, p2);
	}

	public static Monster selectNewMonster(Player p) {
		System.out.println("Select new Monster: ");
		for (int i = 1; i < p.team.size(); i++) {
			if (p.team.get(i).stats.getHp() > 0)
				System.out.print(p.team.get(i).name + " ");
		}
		System.out.println(" Back");
		boolean k = false;
		while (!k) {
			String s = StdIn.readString();
			for (int i = 1; i < p.team.size(); i++) {
				if (p.team.get(i).name.equals(s) && p.team.get(i).stats.getHp() > 0) {
					Collections.swap(p.team, 0, i);
					k = true;
				}
			}
			if (s.equalsIgnoreCase("back"))
				k = true;
			if (!k)
				System.out.println("Wrong Input!");
		}
		System.out.println(p.name + " selected " + p.team.get(0).name + "\r\n");
		return p.team.get(0);
	}

	private Player selectPlayer(Player p1, Player p2) {
		System.out.println("Select Player: ");
		System.out.println(p1.name + " " + p2.name);
		while (true) {
			String s = StdIn.readString();
			if (s.equals(p1.name))
				return p1;
			else if (s.equals(p2.name))
				return p2;
			System.out.println("Wrong Input");
		}
	}

	private void changeMoves(Player p) {
		System.out.println("Select Monster: ");
		for (Monster m : p.team) {
			System.out.print(m.name + " ");
		}
		boolean k = false;
		while (!k) {
			String s = StdIn.readString();
			for (Monster m : p.team) {
				if (m.name.equalsIgnoreCase(s)) {
					m.fightMoves = m.setMoves();
					k = true;
				}
			}
			if (!k)
				System.out.println("Wrong Input!");
		}
	}

	private void changeStarter(Player p) {
		System.out.println("Select new Starting-Monster: ");
		for (int i = 1; i < p.team.size(); i++) {
			System.out.print(p.team.get(i).name + " ");
		}
		System.out.println("Back");
		boolean k = false;
		while (!k) {
			String s = StdIn.readString();
			for (int i = 1; i < p.team.size(); i++) {
				if (p.team.get(i).name.equals(s)) {
					Collections.swap(p.team, 0, i);
					k = true;
				}
			}
			if (s.equalsIgnoreCase("back"))
				k = true;
			if (!k)
				System.out.println("Wrong Input!");
		}
		System.out.println(p.team.get(0).name + " is your new starter!\r\n");
	}

	private void preFightManagement(Player p) {
		System.out.println("Select Action: ");
		System.out.println("Switch Moves");

		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("switch")) {
				changeStarter(p);
				return;
			} else if (s.equalsIgnoreCase("moves")) {
				changeMoves(p);
				return;
			}
			System.out.println("Wrong Input!");
		}
	}

	public static void swap(Player p, String s) {
		for (int i = 0; i < p.team.size(); i++) {
			if (p.team.get(i).name.equals(s)) {
				Collections.swap(p.team, 0, i);
			}
		}
	}

	static void Heal(Player p) {
		for (Monster m : p.team) {
			m.stats.setHp(m.stats.getMaxHp());
		}
		System.out.println(p.name + "'s Team has been healed!\r\n");
	}
}

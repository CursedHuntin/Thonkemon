package main.java.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.java.game.fight.Fight;
import main.java.game.fight.RandomEncounter;

public class Selection {
	Player p1;
	Player p2;

	public Selection(Player p) {
		p1 = p;
		p2 = new Player("Player 2");
	}

	public Selection(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		while (true) {
			actionSelection();
		}
	}

	public Selection() {
		p1 = load();
		while (true)
			actionSelection();
	}

	private void actionSelection() {
		System.out.println("Select: ");
		System.out.println("Fight Team Box Random Heal Store Save");
		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("fight")) {
				System.out.println("Select the amount of players: ");
				System.out.println("1 2");
				while (true) {
					s = StdIn.readString();
					if (s.equalsIgnoreCase("1"))
						new Fight(p1, p2, false);
					else if (s.equalsIgnoreCase("2"))
						new Fight(p1, p2, true);
					return;
				}
			} else if (s.equalsIgnoreCase("team"))
				new TeamManagement(p1, p2);
			else if (s.equalsIgnoreCase("box")) {
				p1.manageBox();
				return;
			} else if (s.equalsIgnoreCase("random")) {
				new RandomEncounter(p1);
				return;
			} else if (s.equalsIgnoreCase("heal")) {
				TeamManagement.Heal(p1);
				return;
			} else if (s.equalsIgnoreCase("store")) {
				new Store(p1);
				return;
			} else if (s.equalsIgnoreCase("save")) {
				save();
				return;
			} else
				System.out.println("Wrong Input!");
		}
	}

	protected void save() {
		try {
			FileOutputStream saveFile = new FileOutputStream("saveFile.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			System.out.println(p1.name);
			save.writeObject(p1);
			save.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Player load() {
		try {
			FileInputStream saveFile = new FileInputStream("saveFile.sav");
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			Player p = (Player) restore.readObject();
			restore.close();
			return p;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

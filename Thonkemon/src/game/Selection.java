package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import game.fight.PlayerFight;
import game.fight.RandomEncounter;

public class Selection implements Serializable {
	private static final long serialVersionUID = 8717791949705931832L;
	Player p1;
	Player p2;

	public Selection(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		System.out.println(p1);
		while (true) {
			actionSelection(p1, p2);
		}
	}

	public Selection() {
		load();
		System.out.println(p1);
		while (true)
			actionSelection(p1, p2);
	}

	private void actionSelection(Player p1, Player p2) {
		System.out.println("Select: ");
		System.out.println("Fight Team Box Random Heal Save");
		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("fight")) {
				new PlayerFight(p1, p2);
				return;
			} else if (s.equalsIgnoreCase("team"))
				new TeamManagement(p1, p2);
			else if (s.equalsIgnoreCase("box")) {
				System.out.println("Select Player: ");
				System.out.println(p1.name + " " + p2.name + " Back");
				while (true) {
					s = StdIn.readString();
					if (p1.name.equalsIgnoreCase(s)) {
						p1.manageBox();
						return;
					} else if (p2.name.equalsIgnoreCase(s)) {
						p2.manageBox();
						return;
					} else if (s.equalsIgnoreCase("back"))
						return;
					else
						System.out.println("Wrong Input!");
				}
			} else if (s.equalsIgnoreCase("random")) {
				System.out.println("Select Player: ");
				System.out.println(p1.name + " " + p2.name);
				while (true) {
					s = StdIn.readString();
					if (p1.name.equals(s)) {
						new RandomEncounter(p1);
						return;
					} else if (p2.name.equals(s)) {
						new RandomEncounter(p2);
						return;
					} else
						System.out.println("Wrong Input!");
				}
			} else if (s.equalsIgnoreCase("heal")) {
				System.out.println("Select Player: ");
				System.out.println(p1.name + " " + p2.name + " Back");
				while (true) {
					s = StdIn.readString();
					if (p1.name.equals(s)) {
						TeamManagement.Heal(p1);
						return;
					} else if (p2.name.equals(s)) {
						TeamManagement.Heal(p2);
						return;
					} else if (s.equalsIgnoreCase("back"))
						return;
					else
						System.out.println("Wrong Input!");
				}
			} else if (s.equalsIgnoreCase("save")) {
				save();
			} else
				System.out.println("Wrong Input!");
		}
	}

	private void save() {
		try {
			FileOutputStream saveFile = new FileOutputStream("saveFile.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			System.out.println(this.toString());
			save.writeObject(this);
			save.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Selection load() {
		try {
			FileInputStream saveFile = new FileInputStream("saveFile.sav");
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			Selection sel = (Selection) restore.readObject();
			restore.close();
			return sel;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

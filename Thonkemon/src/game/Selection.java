package game;

public class Selection {
	public Selection(Player p1, Player p2) {
		while (true) {
			actionSelection(p1, p2);
		}
	}

	private void actionSelection(Player p1, Player p2) {
		System.out.println("Select: ");
		System.out.println("Fight Team Random Heal");
		while (true) {
			String s = StdIn.readString();
			if (s.equals("Fight")) {
				new Fight(p1, p2);
				return;
			} else if (s.equals("Team"))
				new TeamManagement(p1, p2);
			else if (s.equalsIgnoreCase("random")) {
				System.out.println("Select Player: ");
				System.out.println(p1.name + " " + p2.name);
				while (true) {
					s = StdIn.readString();
					if (p1.name.equals(s)) {
						new Fight(p1);
						return;
					} else if (p2.name.equals(s)) {
						new Fight(p2);
						return;
					} else
						System.out.println("Wrong Input!");
				}
			} else if (s.equalsIgnoreCase("heal")) {
				System.out.println("Select Player: ");
				System.out.println(p1.name + " " + p2.name);
				while (true) {
					s = StdIn.readString();
					if (p1.name.equals(s)) {
						TeamManagement.Heal(p1);
						return;
					} else if (p2.name.equals(s)) {
						TeamManagement.Heal(p2);
						return;
					} else
						System.out.println("Wrong Input!");
				}
			} else
				System.out.println("Wrong Input!");
		}
	}
}

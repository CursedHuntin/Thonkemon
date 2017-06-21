package game;

public class Selection {
	public Selection(Player p1, Player p2) {
		fightOrManagement(p1, p2);
	}

	private void fightOrManagement(Player p1, Player p2) {
		System.out.println("Select: ");
		System.out.println("Fight Team");
		while (true) {
			String s = StdIn.readString();
			if (s.equals("Fight")) {
				new Fight(p1, p2);
				return;
			} else if (s.equals("Team"))
				new TeamManagement(p1, p2);
			else
				System.out.println("Wrong Input!");
		}
	}
}

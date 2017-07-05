package game;

public class Startup {
	public static void main(String[] args) {

		System.out.println("Do you want to load an already existing game or start a new game?");
		System.out.println("Load New ");
		while (true) {
			String s = StdIn.readString();
			if (s.equalsIgnoreCase("load")) {
				new Selection();
			} else if (s.equalsIgnoreCase("new"))
				new Selection(new Player(), new Player("Player 2"));
		}

		// new Fight(new Player());
		// Player p1 = new Player();
		// Player p2 = new Player();
		// new Selection(p1, p2);
	}
}

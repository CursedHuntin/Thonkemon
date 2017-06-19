package game;

public class Startup {
	public static void main(String[] args) {
		Player p1 = new Player();
		Player p2 = new Player();
		Fight.fight(p1, p2);
	}
}

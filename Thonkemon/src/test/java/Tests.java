package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import main.java.game.Player;
import main.java.monsters.Krato;
import main.java.monsters.Monster;

public class Tests {

	Player p = new Player("Player");

	@Ignore
	@Test
	public void levelUpTest() {
		Monster m = new Krato(1);
		m.applyEXP(new Krato(23));
		System.out.println(m.exp.level);
		System.out.println(m.exp.exp);
		assertEquals(5, m.exp.level);
	}

	@Ignore
	@Test
	public void randomPlayerTest() {
		Player q = p.createRandomPlayer();
		System.err.println(q.team.size());
		for (Monster m : q.team) {
			System.out.println(m.name);
		}
	}
}

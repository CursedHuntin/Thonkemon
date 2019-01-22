package main.java.game.fight;

import main.java.game.Player;
import main.java.monsters.Monster;

public class PlayerPvP extends Fight implements Runnable {
	Player p1;
	Player p2;

	public PlayerPvP(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void run() {
		playerFight(p1, p2);
	}

	private void playerFight(Player p1, Player p2) {
		Monster p1M = getActiveMonster(p1);
		Monster p2M = getActiveMonster(p2);

		while (!checkIfTeamHasNoHP(p1) && !checkIfTeamHasNoHP(p2)) {
			while (p1M.stats.hp > 0 && p2M.stats.hp > 0) {
				p1M = getActiveMonster(p1);
				p2M = getActiveMonster(p2);
				turn = getTurn(p1M, p2M);
				alternatingTurns(p1, p2, p1M, p2M);

			}
			if (p1M.stats.hp <= 0) {
				p1M = checkIfPlayerLost(p1, p1M);
				if (p1M == null) {
					System.out.println(p2.name + " wins.");
					p1.restoreStats();
					p2.restoreStats();
					return;
				}
			} else {
				p2M = checkIfPlayerLost(p2, p1M);
				if (p2M == null) {
					System.out.println(p1.name + " wins.");
					p1.restoreStats();
					p2.restoreStats();
					return;
				}
			}
		}
	}

	private void alternatingTurns(Player p1, Player p2, Monster p1M, Monster p2M) {
		if (turn % 2 == 0) {
			playerTurn(p1, p2, p1M, p2M);
			if (checkIfMonsterFainted(p2M))
				return;
			p1M = p1.team.get(0);
			playerTurn(p2, p1, p2M, p1M);
		} else {
			playerTurn(p2, p1, p2M, p1M);
			if (checkIfMonsterFainted(p1M))
				return;
			p2M = p2.team.get(0);
			playerTurn(p1, p2, p1M, p2M);
		}
	}

}

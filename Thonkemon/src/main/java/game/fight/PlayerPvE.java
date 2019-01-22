package main.java.game.fight;

import main.java.game.Player;
import main.java.monsters.Monster;
import main.java.moves.Move;

public class PlayerPvE extends Fight {
	Player p1;
	Player p2;

	public PlayerPvE(Player p1) {
		this.p1 = p1;
		p2 = p1.createRandomPlayer();

	}

	public PlayerPvE(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void run() {
		fight(p1, p2);
	}

	public void fight(Player p1, Player p2) {

		p2.isNPC = true;
		// p2.team.get(0).stats.hp = 1;

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

	void alternatingTurns(Player p1, Player p2, Monster p1M, Monster p2M) {
		if (turn % 2 == 0) {
			playerTurn(p1, p2, p1M, p2M);
			if (checkIfMonsterFainted(p2M))
				return;
			p1M = p1.team.get(0);
			randomTurn(p2, p1, p2M, p1M);
		} else {
			randomTurn(p2, p1, p2M, p1M);
			if (checkIfMonsterFainted(p1M))
				return;
			p2M = p2.team.get(0);
			playerTurn(p1, p2, p1M, p2M);
		}
	}

	private void randomTurn(Player p2, Player p1, Monster p2m, Monster p1m) {
		System.out.println(p2.name + "'s turn!");
		executeRandomMove(p2, p2m, p1, p1m, getRandomMove(p2m));
		turn++;
		skipTurn = false;
	}

	private void executeRandomMove(Player p2, Monster p2m, Player p1, Monster p1m, Move randomMove) {
		for (Move move : p2m.fightMoves) {
			if (move.name.equals(randomMove.name)) {
				if (hit(move) && skipTurn != true) {
					onMoveHit(p2, p1, p2m, p1m, move);
				} else {
					onMoveMissed(p2, p1, p2m, p1m, move);
				}
				return;
			}
		}
	}
}

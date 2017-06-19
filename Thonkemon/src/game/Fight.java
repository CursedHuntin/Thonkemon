package game;

import items.Ball;
import monsters.Monster;
import moves.Move;
import types.Type;

public class Fight {
	static int turn;
	private int p1c;
	private int p2c;
	public static boolean skipTurn;

	public Fight(Player p1, Player p2) {
		fight(p1, p2);
	}

	public void fight(Player p1, Player p2) {

		Monster p1M = p1.team.get(0);
		Monster p2M = p2.team.get(0);
		p1c = 0;
		p2c = 0;

		while (p1.team.size() > p1c && p2.team.size() > p2c) {
			while (p1M.stats.getHp() > 0 && p2M.stats.getHp() > 0) {
				turn = getTurn(p1M, p2M);
				alternatingTurns(p1, p2, p1M, p2M);

			}
			if (p1M.stats.getHp() <= 0) {
				p1M = checkIfP1Fainted(p1, p1M, p2);
			} else {
				p2M = checkIfP2Fainted(p2, p2M, p1);
			}
		}
	}

	// wer greift zuerst an?
	private int getTurn(Monster p1m, Monster p2m) {
		return (p2m.stats.getInit() > p1m.stats.getInit() ? 1 : 0);
	}

	private void alternatingTurns(Player p1, Player p2, Monster p1M, Monster p2M) {
		if (turn % 2 == 0) {
			playerTurn(p1, p2, p1M, p2M);
			if (checkIfMonsterFainted(p2M))
				return;
			playerTurn(p2, p1, p2M, p1M);
		} else {
			playerTurn(p2, p1, p2M, p1M);
			if (checkIfMonsterFainted(p1M))
				return;
			playerTurn(p1, p2, p1M, p2M);
		}
	}

	private boolean checkIfMonsterFainted(Monster m) {
		return m.stats.getHp() <= 0;
	}

	private void playerTurn(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM) {
		System.out.println(atkPlayer.name + "'s turn!");
		System.out.println("Select Move: ");
		for (Move move : atkPM.moves) {
			System.out.print(move.name + " ");
		}
		System.out.println("\r\nSwitch");
		boolean k = false;
		while (k != true) {
			String m = StdIn.readString();
			if (m.equals("Switch")) {
				atkPM = selectNewMonster(atkPlayer, atkPM, m);
				k = true;
			} else {
				for (Move move : atkPM.moves) {
					if (move.name.equals(m)) {
						if (move.status != null) {
							move.status.applyEffect(defPM);
						}

						if (defPM.status != null)
							defPM.status.effect(defPM);

						if (hit(move) && skipTurn != true) {

							// Konsolendokumentation des Kampfes + HP-Änderung
							if (move.damage > 0)
								System.out.println(
										move.name + " did " + calculateDamage(atkPM, defPM, move) + " damage.");
							System.out.println(
									atkPlayer.name + "'s " + atkPM.name + " is at " + atkPM.stats.getHp() + " HP");
							if (defPM.stats.getHp() > calculateDamage(atkPM, defPM, move))
								System.out.println(defPlayer.name + "'s " + defPM.name + " is at "
										+ (defPM.stats.getHp() - calculateDamage(atkPM, defPM, move)) + " HP."
										+ "\r\n");
							defPM.stats.setHp(defPM.stats.getHp() - calculateDamage(atkPM, defPM, move));
						} else {
							System.out.println(atkPM.name + "'s " + move.name + " missed!");
							System.out.println(
									atkPlayer.name + "'s " + atkPM.name + " is at " + atkPM.stats.getHp() + " HP");
							System.out.println(
									defPlayer.name + "'s " + defPM.name + " is at " + defPM.stats.getHp() + " HP");
						}
						k = true;
					}
				}
			}
			if (!k)
				System.out.println("Wrong Input!");
		}
		turn++;
		skipTurn = false;
	}

	private Monster selectNewMonster(Player atkPlayer, Monster atkPM, String m) {
		System.out.println("Select new Monster: ");
		boolean k = false;
		for (Monster n : atkPlayer.team) {
			System.out.println(n.name);
		}
		while (!k) {
			String newMonster = StdIn.readString();
			for (Monster n : atkPlayer.team) {
				if (n.name.equals(newMonster) && !atkPM.name.equals(newMonster)) {
					atkPM = switchMon(atkPlayer, atkPM, n.name);
					k = true;
				}
			}
			if (!k)
				System.out.println("Wrong Input!");
		}
		return atkPM;
	}

	private Monster checkIfP1Fainted(Player p1, Monster p1M, Player p2) {
		p1c++;
		if (p1c < p1.team.size()) {
			System.out.println(p1.name + "'s " + p1M.name + " fainted.");
			p1M = p1.team.get(p1c);
			System.out.println(p1.name + " switched to " + p1M.name);
		} else {
			System.out.println("All of " + p1.name + "'s Monster fainted.");
			System.out.println(p2.name + " wins.");
		}
		return p1M;
	}

	private Monster checkIfP2Fainted(Player p2, Monster p2M, Player p1) {
		p2c++;
		if (p2c < p2.team.size()) {
			System.out.println(p2.name + "'s " + p2M.name + " fainted.");
			p2M = p2.team.get(p2c);
			System.out.println(p2.name + " switched to " + p2M.name);
		} else {
			System.out.println("All of " + p2.name + "'s Monster fainted.");
			System.out.println(p1.name + " wins.");
		}
		return p2M;
	}

	private int calculateDamage(Monster a, Monster b, Move m) {
		if (m.damage == 0)
			return 0;
		double userAtk, oppDef;
		// Schadenskalkulation nach physischem oder magischem Schaden
		if (m.damageType.name.equals("Physical")) {
			userAtk = (2 * a.stats.getLevel() + 10) * a.stats.getAtk() * m.damage;
			oppDef = 250 * b.stats.getDef();
		} else {
			userAtk = (2 * a.stats.getLevel() + 10) * a.stats.getSpAtk() * m.damage;
			oppDef = 250 * b.stats.getSpDef();
		}

		return (int) (((userAtk / oppDef + 2) * Type.getSTAB(m.type, a) * Type.getMult(m.type, b)));

	}

	private boolean catchMon(Player p, Monster m, Ball b) {
		if (p.team.size() < 6)
			return ((m.stats.getCatchrate() + b.catchrate) / 2) > (Math.random() * 100);
		else {
			System.out.println("Your team is already full!");
			return false;
		}
	}

	private void monCought(Player p, Monster m) {
		p.addMonsterToTeam(p.team, m);
	}

	private Monster switchMon(Player p, Monster active, String nextMon) {
		for (Monster m : p.team) {
			if (m.name.equals(nextMon)) {
				System.out.println(p.name + " switched to " + nextMon);
				return m;
			}
		}
		System.out.println("Didn't switch!");
		return active;
	}

	private boolean hit(Move m) {
		boolean hit = true;
		if ((Math.random() * 100) > m.accuracy)
			hit = false;
		return hit;
	}
}

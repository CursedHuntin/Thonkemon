package main.java.game.fight;

import main.java.game.Player;
import main.java.game.StdIn;
import main.java.game.TeamManagement;
import main.java.items.Item;
import main.java.monsters.Monster;
import main.java.moves.Move;
import main.java.types.Type;

public class Fight {
	static int turn;
	public static boolean skipTurn;
	static boolean monCaught = false;

	public Fight() {

	}

	public Fight(Player p) {
		new PlayerPvE(p);
	}

	public Fight(Player p1, Player p2, boolean b) {
		if (b)
			new PlayerPvP(p1, p2);
		else
			new PlayerPvE(p1, p2);
	}

	// public Fight(Player p, Monster m) {
	// new RandomEncounter(p, m);
	// }

	Monster getActiveMonster(Player p) {
		for (Monster m : p.team) {
			if (m.stats.hp > 0)
				return m;
		}
		return p.team.get(0);
	}

	// wer greift zuerst an?
	int getTurn(Monster p1m, Monster p2m) {
		return (p2m.stats.init > p1m.stats.init ? 1 : 0);
	}

	boolean checkIfMonsterFainted(Monster m) {
		return m.stats.hp <= 0;
	}

	void selectItem(Player atkPlayer, Monster atkPM) {
		for (Item item : atkPlayer.items) {
			if (item.amount > 0)
				System.out.print(item.name + "(" + item.amount + ") ");
		}
		System.out.println("");
		boolean k = false;
		while (k == false) {
			String s = StdIn.readString();
			for (Item item : atkPlayer.items) {
				if (item.name.equalsIgnoreCase(s)) {
					if (item.amount > 0) {
						item.effect(atkPM);
						item.amount--;
						k = true;
					}
				}
			}
			if (k == false) {
				System.out.println("Select proper Item!");
			}
		}
	}

	void applyEffects(Monster defPM, Move move) {
		if (move.status != null)
			move.status.applyEffect(defPM);

		if (defPM.status != null)
			defPM.status.effect(defPM);
	}

	Monster selectNewMonster(Player atkPlayer, Monster atkPM) {
		System.out.println("Select new Monster: ");
		boolean k = false;
		for (Monster n : atkPlayer.team) {
			System.out.print(n.name + " ");
		}
		System.out.println("");
		while (!k) {
			String newMonster = StdIn.readString();
			for (Monster n : atkPlayer.team) {
				if (n.name.equals(newMonster) && !atkPM.name.equals(newMonster)) {
					switchMon(atkPlayer, atkPM, n.name);
					k = true;
				}
			}
			if (!k)
				System.out.println("Wrong Input!");
		}
		return atkPM;
	}

	Monster checkIfPlayerLost(Player p, Monster p1M) {
		for (Monster m : p.team) {
			if (m.stats.hp > 0) {
				System.out.println(p.name + "'s " + p1M.name + " fainted.");
				if (!p.isNPC)
					p1M = selectNewMonster(p, p1M);
				else
					p1M = selectRandomMonster(p, p1M);
				return p1M;
			}
		}
		System.out.println("All of " + p.name + "'s Monster fainted.");
		return null;
	}

	private Monster selectRandomMonster(Player p, Monster p1m) {
		for (Monster m : p.team) {
			if (m.stats.hp > 0)
				return p1m;
		}
		return null;
	}

	int calculateDamage(Monster a, Monster b, Move m) {
		if (m.damage == 0)
			return 0;
		double userAtk, oppDef;
		// Schadenskalkulation nach physischem oder magischem Schaden
		if (m.damageType.name.equals("Physical")) {
			userAtk = (2 * a.exp.level + 10) * a.stats.atk * m.damage;
			oppDef = 250 * b.stats.def;
		} else {
			userAtk = (2 * a.exp.level + 10) * a.stats.spAtk * m.damage;
			oppDef = 250 * b.stats.spDef;
		}

		return (int) (((userAtk / oppDef + 2) * Type.getSTAB(m.type, a) * Type.getMult(m.type, b)));

	}

	void switchMon(Player p, Monster active, String nextMon) {
		TeamManagement.swap(p, nextMon);
		System.out.println(p.name + " switched to " + nextMon);
	}

	boolean hit(Move m) {
		boolean hit = true;
		if ((Math.random() * 100) > m.accuracy)
			hit = false;
		return hit;
	}

	boolean checkIfTeamHasNoHP(Player p) {
		for (Monster m : p.team) {
			if (m.stats.hp > 0) {
				return false;
			}
		}
		return true;
	}

	void printSelection(Monster atkPM) {
		System.out.println("Select Move: ");
		for (Move move : atkPM.fightMoves) {
			if (move.equals(null))
				System.out.println("null");
			System.out.print(move.name + " ");
		}
		System.out.println("\r\nSwitch Item");
	}

	void playerTurn(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM) {

		System.out.println(atkPlayer.name + "'s turn!");
		printSelection(atkPM);
		boolean k = false;
		while (k != true) {
			String m = StdIn.readString();
			if (m.equalsIgnoreCase("switch")) {
				atkPM = selectNewMonster(atkPlayer, atkPM);
				k = true;

			} else if (m.equalsIgnoreCase("item")) {
				selectItem(atkPlayer, atkPM);
				k = true;
			} else {
				for (Move move : atkPM.fightMoves) {
					if (move.name.equalsIgnoreCase(m)) {
						executeMove(atkPlayer, defPlayer, atkPM, defPM, move);
						k = true;
					}
				}
			}
			if (!k)
				System.out.println("Select proper Command!");
		}
		turn++;
		skipTurn = false;
	}

	void executeMove(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		if (hit(move) && !skipTurn) {
			onMoveHit(atkPlayer, defPlayer, atkPM, defPM, move);
		} else {
			onMoveMissed(atkPlayer, defPlayer, atkPM, defPM, move);
		}
	}

	void onMoveMissed(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		trainerFightMissedOutput(atkPlayer, defPlayer, atkPM, defPM, move);

	}

	void trainerFightMissedOutput(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		System.out.println(atkPM.name + "'s " + move.name + " missed!");
		System.out.println(atkPlayer.name + "'s " + atkPM.name + " is at " + atkPM.stats.hp + " HP");
		System.out.println(defPlayer.name + "'s " + defPM.name + " is at " + defPM.stats.hp + " HP");
	}

	void onMoveHit(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {

		String atk = atkPlayer.name + "'s ";
		String def = defPlayer.name + "'s ";
		if (atkPlayer.name.equals("Wild")) {
			atk = "";
		} else if (defPlayer.name.equals("Wild")) {
			def = "";
		}

		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(atkPM, defPM, move) + " damage.");
		System.out.println(atk + atkPM.name + " is at " + atkPM.stats.hp + " HP");
		if (defPM.stats.hp > calculateDamage(atkPM, defPM, move))
			System.out.println(def + defPM.name + " is at " + (defPM.stats.hp - calculateDamage(atkPM, defPM, move))
					+ " HP." + "\r\n");
		defPM.stats.hp = (defPM.stats.hp - calculateDamage(atkPM, defPM, move));
		applyEffects(defPM, move);
	}

	Move getRandomMove(Monster m) {
		return m.moves.get((int) (Math.random() * m.moves.size()));
	}

}

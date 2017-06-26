package game.fight;

import game.Player;
import game.StdIn;
import game.TeamManagement;
import items.Item;
import monsters.Monster;
import moves.Move;
import types.Type;

public class Fight {
	static int turn;
	public static boolean skipTurn;
	static boolean monCaught = false;

	Monster getActiveMonster(Player p) {
		for (Monster m : p.team) {
			if (m.stats.getHp() > 0)
				return m;
		}
		return p.team.get(0);
	}

	// wer greift zuerst an?
	int getTurn(Monster p1m, Monster p2m) {
		return (p2m.stats.getInit() > p1m.stats.getInit() ? 1 : 0);
	}

	boolean checkIfMonsterFainted(Monster m) {
		return m.stats.getHp() <= 0;
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
			if (m.stats.getHp() > 0) {
				System.out.println(p.name + "'s " + p1M.name + " fainted.");
				p1M = TeamManagement.selectNewMonster(p);
				return p1M;
			}
		}
		System.out.println("All of " + p.name + "'s Monster fainted.");
		return null;
	}

	int calculateDamage(Monster a, Monster b, Move m) {
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
			if (m.stats.getHp() > 0) {
				return false;
			}
		}
		return true;
	}
}

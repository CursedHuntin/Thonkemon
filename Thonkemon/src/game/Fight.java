package game;

import java.util.ArrayList;
import java.util.List;

import items.Item;
import items.balls.Ball;
import monsters.Buhrn;
import monsters.Krato;
import monsters.Monster;
import monsters.Wotah;
import moves.Move;
import types.Type;

public class Fight {
	static int turn;
	public static boolean skipTurn;

	public Fight(Player p1, Player p2) {
		fight(p1, p2);
	}

	public Fight(Player p) {
		randomEncounter(p);
	}

	public void fight(Player p1, Player p2) {
		Monster p1M = p1.team.get(0);
		Monster p2M = p2.team.get(0);

		while (!checkIfTeamHasNoHP(p1) && !checkIfTeamHasNoHP(p2)) {
			while (p1M.stats.getHp() > 0 && p2M.stats.getHp() > 0) {
				p1M = p1.team.get(0);
				p2M = p2.team.get(0);
				turn = getTurn(p1M, p2M);
				alternatingTurns(p1, p2, p1M, p2M);

			}
			if (p1M.stats.getHp() <= 0) {
				p1M = checkIfPlayerLost(p1, p1M);
				if (p1M == null) {
					System.out.println(p2.name + " wins.");
					return;
				}
			} else {
				p2M = checkIfPlayerLost(p2, p1M);
				if (p2M == null) {
					System.out.println(p1.name + " wins.");
					return;
				}
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

	private boolean checkIfMonsterFainted(Monster m) {
		return m.stats.getHp() <= 0;
	}

	private void playerTurn(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM) {
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
				k = executeMove(atkPlayer, defPlayer, atkPM, defPM, k, m);
			}
			if (!k)
				System.out.println("Select proper Command!");
		}
		turn++;
		skipTurn = false;
	}

	private void selectItem(Player atkPlayer, Monster atkPM) {
		for (Item item : atkPlayer.items) {
			if (item.amount > 0)
				System.out.print(item.name + " ");
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

	private boolean executeMove(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, boolean k, String m) {
		for (Move move : atkPM.moves) {
			if (move.name.equals(m)) {
				if (hit(move) && skipTurn != true) {
					onMoveHit(atkPlayer, defPlayer, atkPM, defPM, move);
				} else {
					onMoveMissed(atkPlayer, defPlayer, atkPM, defPM, move);
				}
				k = true;
			}
		}
		return k;
	}

	private void printSelection(Monster atkPM) {
		System.out.println("Select Move: ");
		for (Move move : atkPM.moves) {
			System.out.print(move.name + " ");
		}
		System.out.println("\r\nSwitch Item");
	}

	private void applyEffects(Monster defPM, Move move) {
		if (move.status != null)
			move.status.applyEffect(defPM);

		if (defPM.status != null)
			defPM.status.effect(defPM);
	}

	private void onMoveMissed(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		System.out.println(atkPM.name + "'s " + move.name + " missed!");
		System.out.println(atkPlayer.name + "'s " + atkPM.name + " is at " + atkPM.stats.getHp() + " HP");
		System.out.println(defPlayer.name + "'s " + defPM.name + " is at " + defPM.stats.getHp() + " HP");
	}

	private void onMoveHit(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		applyEffects(defPM, move);
		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(atkPM, defPM, move) + " damage.");
		System.out.println(atkPlayer.name + "'s " + atkPM.name + " is at " + atkPM.stats.getHp() + " HP");
		if (defPM.stats.getHp() > calculateDamage(atkPM, defPM, move))
			System.out.println(defPlayer.name + "'s " + defPM.name + " is at "
					+ (defPM.stats.getHp() - calculateDamage(atkPM, defPM, move)) + " HP." + "\r\n");
		defPM.stats.setHp(defPM.stats.getHp() - calculateDamage(atkPM, defPM, move));
	}

	private Monster selectNewMonster(Player atkPlayer, Monster atkPM) {
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

	private Monster checkIfPlayerLost(Player p, Monster p1M) {
		for (Monster m : p.team) {
			if (m.stats.getHp() > 0) {
				System.out.println(p.name + "'s " + p1M.name + " fainted.");
				p1M = TeamManagement.selectNewMonster(p);
				System.out.println(p.name + " switched to " + p1M.name);
				return p1M;
			}
		}
		System.out.println("All of " + p.name + "'s Monster fainted.");
		return null;
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

	private void switchMon(Player p, Monster active, String nextMon) {
		TeamManagement.swap(p, nextMon);
		System.out.println(p.name + " switched to " + nextMon);
	}

	private boolean hit(Move m) {
		boolean hit = true;
		if ((Math.random() * 100) > m.accuracy)
			hit = false;
		return hit;
	}

	private boolean checkIfTeamHasNoHP(Player p) {
		for (Monster m : p.team) {
			if (m.stats.getHp() > 0) {
				return false;
			}
		}
		return true;
	}

	private void randomEncounter(Player p) {
		Player p2 = createRandomMonster();
		fight(p, p2);
	}

	private Player createRandomMonster() {
		int level = (int) (Math.random() * 99) + 1;
		int m = (int) (Math.random() * (createMonsters(level).size()));
		Monster mon = createMonsters(level).get(m);
		Player p2 = new Player(mon.name, mon);
		return p2;
	}

	private List<Monster> createMonsters(int level) {
		List<Monster> monster = new ArrayList<Monster>();
		monster.add(new Wotah(level));
		monster.add(new Krato(level));
		monster.add(new Buhrn(level));
		return monster;
	}

	private Move getRandomMove(Monster m) {
		return m.moves.get((int) (Math.random() * m.moves.size()));
	}
}

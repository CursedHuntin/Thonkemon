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
	private static boolean monCaught = false;

	public Fight(Player p1, Player p2) {
		playerFight(p1, p2);
	}

	public Fight(Player p) {
		randomEncounter(p);
		monCaught = false;
	}

	private void playerFight(Player p1, Player p2) {
		Monster p1M = getActiveMonster(p1);
		Monster p2M = getActiveMonster(p2);

		while (!checkIfTeamHasNoHP(p1) && !checkIfTeamHasNoHP(p2)) {
			while (p1M.stats.getHp() > 0 && p2M.stats.getHp() > 0) {
				p1M = getActiveMonster(p1);
				p2M = getActiveMonster(p2);
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

	private void randomEncounterFight(Player p, Monster m) {
		Monster pm = getActiveMonster(p);

		System.out.println("Encountered a " + m.name + " Lv" + m.stats.getLevel());
		while (!checkIfTeamHasNoHP(p) && !monCaught) {
			while (pm.stats.getHp() > 0 && m.stats.getHp() > 0 && !monCaught) {
				pm = getActiveMonster(p);
				turn = getTurn(pm, m);
				randomEncounterAlternatingTurns(p, m, pm);

			}
			if (pm.stats.getHp() <= 0) {
				pm = checkIfPlayerLost(p, pm);
				if (pm == null) {
					System.out.println(p.name + " fainted.");
					return;
				}
			} else if (monCaught)
				return;
			else {
				System.out.println(m.name + " fainted.");
				System.out.println(p.name + " wins.");
				return;
			}
		}
	}

	private void randomEncounterAlternatingTurns(Player p, Monster m, Monster pm) {
		if (turn % 2 == 0) {
			playerTurnAgainstMonster(p, m, pm);
			if (checkIfMonsterFainted(m))
				return;
			pm = getActiveMonster(p);
			randomTurn(p, m, pm);
		} else {
			randomTurn(p, m, pm);
			if (checkIfMonsterFainted(pm))
				return;
			playerTurnAgainstMonster(p, m, pm);
		}
	}

	private void playerTurnAgainstMonster(Player p, Monster m, Monster pm) {
		if (!monCaught) {
			System.out.println(p.name + "'s turn!");
			printSelectionOnEncounter(pm);
			boolean k = false;
			while (k != true) {
				String s = StdIn.readString();
				if (s.equalsIgnoreCase("switch")) {
					pm = selectNewMonster(p, pm);
					k = true;

				} else if (s.equalsIgnoreCase("item")) {
					selectItem(p, pm);
					k = true;
				} else if (s.equalsIgnoreCase("catch")) {
					Ball b = selectBall(p);
					if (b == null)
						return;
					if (catchMon(p, m, b)) {
						monCought(p, m);
						return;
					} else
						System.out.println(m.name + " managed to escape!");

				} else {
					for (Move move : pm.moves) {
						if (move.name.equals(s)) {
							executeMoveAgainstMonster(p, m, pm, move);
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
	}

	private void executeMoveAgainstMonster(Player p, Monster m, Monster pm, Move move) {
		if (hit(move) && skipTurn != true) {
			onMoveHitPlayerAttacking(m, p, pm, move);
		} else {
			onMoveMissedPlayerAttacking(m, p, pm, move);
		}
	}

	private void randomTurn(Player p, Monster m, Monster pm) {
		if (!monCaught) {
			System.out.println(m.name + "'s turn!");
			executeRandomMove(p, m, pm, getRandomMove(m));
			turn++;
			skipTurn = false;
		}
	}

	private void executeRandomMove(Player p, Monster m, Monster pm, Move randomMove) {
		for (Move move : m.moves) {
			if (move.name.equals(randomMove.name)) {
				if (hit(move) && skipTurn != true) {
					onMoveHitMonsterAttacking(m, p, pm, move);
				} else {
					onMoveMissedMonsterAttacking(m, p, pm, move);
				}
				return;
			}
		}
	}

	private void onMoveMissedMonsterAttacking(Monster m, Player p, Monster pm, Move move) {
		randomFightMissedOutputMonsterAttacking(m, p, pm, move);
	}

	private void onMoveMissedPlayerAttacking(Monster m, Player p, Monster pm, Move move) {
		randomFightMissedOutputPlayerAttacking(m, p, pm, move);
	}

	private Monster getActiveMonster(Player p) {
		for (Monster m : p.team) {
			if (m.stats.getHp() > 0)
				return m;
		}
		return p.team.get(0);
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
				for (Move move : atkPM.moves) {
					if (move.name.equals(m)) {
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

	private void selectItem(Player atkPlayer, Monster atkPM) {
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

	private Ball selectBall(Player p) {
		boolean k = false;
		for (Ball b : p.balls) {
			if (b.amount > 0) {
				System.out.print(b.name + "(" + b.amount + ") ");
				k = true;
			}
		}
		if (k == false) {
			System.out.println("No Balls available!");
			return null;
		}
		System.out.println("");
		while (true) {
			String s = StdIn.readString();
			for (Ball b : p.balls) {
				if (b.name.equalsIgnoreCase(s)) {
					if (b.amount > 0) {
						b.amount--;
						return b;
					}
				}
			}
			System.out.println("Select proper Ball!");

		}
	}

	private void executeMove(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		if (hit(move) && skipTurn != true) {
			onMoveHit(atkPlayer, defPlayer, atkPM, defPM, move);
		} else {
			onMoveMissed(atkPlayer, defPlayer, atkPM, defPM, move);
		}
	}

	private void printSelection(Monster atkPM) {
		System.out.println("Select Move: ");
		for (Move move : atkPM.moves) {
			System.out.print(move.name + " ");
		}
		System.out.println("\r\nSwitch Item");
	}

	private void printSelectionOnEncounter(Monster atkPM) {
		System.out.println("Select Move: ");
		for (Move move : atkPM.moves) {
			System.out.print(move.name + " ");
		}
		System.out.println("\r\nSwitch Item Catch");
	}

	private void applyEffects(Monster defPM, Move move) {
		if (move.status != null)
			move.status.applyEffect(defPM);

		if (defPM.status != null)
			defPM.status.effect(defPM);
	}

	private void onMoveMissed(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		trainerFightMissedOutput(atkPlayer, defPlayer, atkPM, defPM, move);

	}

	private void randomFightMissedOutputMonsterAttacking(Monster m, Player defPlayer, Monster defPM, Move move) {

		System.out.println(m.name + "'s " + move.name + " missed!");
		System.out.println(m.name + " is at " + m.stats.getHp() + " HP");
		System.out.println(defPlayer.name + "'s " + defPM.name + " is at " + defPM.stats.getHp() + " HP");
	}

	private void randomFightMissedOutputPlayerAttacking(Monster m, Player defPlayer, Monster defPM, Move move) {

		System.out.println(defPM.name + "'s " + move.name + " missed!");
		System.out.println(defPlayer.name + "'s " + defPM.name + " is at " + defPM.stats.getHp() + " HP");
		System.out.println(m.name + " is at " + m.stats.getHp() + " HP");
	}

	private void trainerFightMissedOutput(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {
		System.out.println(atkPM.name + "'s " + move.name + " missed!");
		System.out.println(atkPlayer.name + "'s " + atkPM.name + " is at " + atkPM.stats.getHp() + " HP");
		System.out.println(defPlayer.name + "'s " + defPM.name + " is at " + defPM.stats.getHp() + " HP");
	}

	private void onMoveHit(Player atkPlayer, Player defPlayer, Monster atkPM, Monster defPM, Move move) {

		String atk = atkPlayer.name + "'s ";
		String def = defPlayer.name + "'s ";
		if (atkPlayer.name.equals("Wild")) {
			atk = "";
		} else if (defPlayer.name.equals("Wild")) {
			def = "";
		}

		applyEffects(defPM, move);
		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(atkPM, defPM, move) + " damage.");
		System.out.println(atk + atkPM.name + " is at " + atkPM.stats.getHp() + " HP");
		if (defPM.stats.getHp() > calculateDamage(atkPM, defPM, move))
			System.out.println(def + defPM.name + " is at "
					+ (defPM.stats.getHp() - calculateDamage(atkPM, defPM, move)) + " HP." + "\r\n");
		defPM.stats.setHp(defPM.stats.getHp() - calculateDamage(atkPM, defPM, move));
	}

	private void onMoveHitMonsterAttacking(Monster m, Player p, Monster pm, Move move) {

		applyEffects(pm, move);
		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(m, pm, move) + " damage.");
		System.out.println(m.name + " is at " + m.stats.getHp() + " HP");
		if (pm.stats.getHp() > calculateDamage(m, pm, move))
			System.out.println(p.name + "'s " + pm.name + " is at " + (pm.stats.getHp() - calculateDamage(m, pm, move))
					+ " HP." + "\r\n");
		pm.stats.setHp(pm.stats.getHp() - calculateDamage(m, pm, move));
	}

	private void onMoveHitPlayerAttacking(Monster m, Player p, Monster pm, Move move) {

		applyEffects(m, move);
		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(pm, m, move) + " damage.");
		System.out.println(p.name + "'s " + pm.name + " is at " + pm.stats.getHp() + " HP");
		if (m.stats.getHp() > calculateDamage(pm, m, move))
			System.out.println(m.name + " is at " + (m.stats.getHp() - calculateDamage(pm, m, move)) + " HP." + "\r\n");
		m.stats.setHp(m.stats.getHp() - calculateDamage(pm, m, move));
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
		System.out.println(m.name + " has been caught!\r\n");
		System.out.println("Select a new Name: ");
		String s = StdIn.readString();
		boolean k = false;
		if (s.equals(m.name.replace("Wild ", ""))) {
			while (!k) {
				System.out.println("Please select a different Name: ");
				s = StdIn.readString();
				if (!s.equals(m.name.replace("Wild ", ""))) {
					m.setNickname(s);
				}
				System.out.println(k);
			}
		} else
			m.setNickname(s);
		p.addMonsterToTeam(p.team, m);
		monCaught = true;

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
		Monster m = createRandomMonster();
		randomEncounterFight(p, m);
	}

	private Monster createRandomMonster() {
		int level = (int) (Math.random() * 99) + 1;
		int m = (int) (Math.random() * (createMonsters(level).size()));
		Monster mon = createMonsters(level).get(m);
		mon.setNickname("Wild " + mon.name);
		return mon;
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

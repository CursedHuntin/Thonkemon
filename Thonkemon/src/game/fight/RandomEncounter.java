package game.fight;

import java.util.ArrayList;
import java.util.List;

import game.Player;
import game.StdIn;
import items.balls.Ball;
import monsters.Buhrn;
import monsters.Krato;
import monsters.Monster;
import monsters.Wotah;
import moves.Move;

public class RandomEncounter extends Fight {

	public RandomEncounter(Player p) {
		randomEncounter(p);
		monCaught = false;
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

	private void printSelectionOnEncounter(Monster atkPM) {
		System.out.println("Select Move: ");
		for (Move move : atkPM.moves) {
			System.out.print(move.name + " ");
		}
		System.out.println("\r\nSwitch Item Catch");
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

	private void onMoveHitMonsterAttacking(Monster m, Player p, Monster pm, Move move) {

		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(m, pm, move) + " damage.");
		System.out.println(m.name + " is at " + m.stats.getHp() + " HP");
		if (pm.stats.getHp() > calculateDamage(m, pm, move))
			System.out.println(p.name + "'s " + pm.name + " is at " + (pm.stats.getHp() - calculateDamage(m, pm, move))
					+ " HP." + "\r\n");
		pm.stats.setHp(pm.stats.getHp() - calculateDamage(m, pm, move));
		applyEffects(pm, move);
	}

	private void onMoveHitPlayerAttacking(Monster m, Player p, Monster pm, Move move) {

		if (move.damage > 0)
			System.out.println(move.name + " did " + calculateDamage(pm, m, move) + " damage.");
		System.out.println(p.name + "'s " + pm.name + " is at " + pm.stats.getHp() + " HP");
		if (m.stats.getHp() > calculateDamage(pm, m, move))
			System.out.println(m.name + " is at " + (m.stats.getHp() - calculateDamage(pm, m, move)) + " HP." + "\r\n");
		m.stats.setHp(m.stats.getHp() - calculateDamage(pm, m, move));
		applyEffects(m, move);
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

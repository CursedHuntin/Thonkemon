package main.java.game.fight;

import main.java.game.Player;

public class PlayerFight extends Fight {

	public PlayerFight(Player p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	//
	// public PlayerFight(Player p1, Player p2) {
	// playerFight(p1, p2);
	// }
	//
	// public PlayerFight(Player p1) {
	// playerFight(p1, p1.createRandomPlayer());
	// }
	//
	// private void playerFight(Player p1, Player p2) {
	// Monster p1M = getActiveMonster(p1);
	// Monster p2M = getActiveMonster(p2);
	//
	// while (!checkIfTeamHasNoHP(p1) && !checkIfTeamHasNoHP(p2)) {
	// while (p1M.stats.hp > 0 && p2M.stats.hp > 0) {
	// p1M = getActiveMonster(p1);
	// p2M = getActiveMonster(p2);
	// turn = getTurn(p1M, p2M);
	// alternatingTurns(p1, p2, p1M, p2M);
	//
	// }
	// if (p1M.stats.hp <= 0) {
	// p1M = checkIfPlayerLost(p1, p1M);
	// if (p1M == null) {
	// System.out.println(p2.name + " wins.");
	// return;
	// }
	// } else {
	// p2M = checkIfPlayerLost(p2, p1M);
	// if (p2M == null) {
	// System.out.println(p1.name + " wins.");
	// return;
	// }
	// }
	// }
	// }
	//
	// private void alternatingTurns(Player p1, Player p2, Monster p1M, Monster
	// p2M) {
	// if (turn % 2 == 0) {
	// playerTurn(p1, p2, p1M, p2M);
	// if (checkIfMonsterFainted(p2M))
	// return;
	// p1M = p1.team.get(0);
	// playerTurn(p2, p1, p2M, p1M);
	// } else {
	// playerTurn(p2, p1, p2M, p1M);
	// if (checkIfMonsterFainted(p1M))
	// return;
	// p2M = p2.team.get(0);
	// playerTurn(p1, p2, p1M, p2M);
	// }
	// }
	//
	// private void playerTurn(Player atkPlayer, Player defPlayer, Monster
	// atkPM, Monster defPM) {
	//
	// System.out.println(atkPlayer.name + "'s turn!");
	// printSelection(atkPM);
	// boolean k = false;
	// while (k != true) {
	// String m = StdIn.readString();
	// if (m.equalsIgnoreCase("switch")) {
	// atkPM = selectNewMonster(atkPlayer, atkPM);
	// k = true;
	//
	// } else if (m.equalsIgnoreCase("item")) {
	// selectItem(atkPlayer, atkPM);
	// k = true;
	// } else {
	// for (Move move : atkPM.fightMoves) {
	// if (move.name.equals(m)) {
	// executeMove(atkPlayer, defPlayer, atkPM, defPM, move);
	// k = true;
	// }
	// }
	// }
	// if (!k)
	// System.out.println("Select proper Command!");
	// }
	// turn++;
	// skipTurn = false;
	// }
	//
	// private void executeMove(Player atkPlayer, Player defPlayer, Monster
	// atkPM, Monster defPM, Move move) {
	// if (hit(move) && skipTurn != true) {
	// onMoveHit(atkPlayer, defPlayer, atkPM, defPM, move);
	// } else {
	// onMoveMissed(atkPlayer, defPlayer, atkPM, defPM, move);
	// }
	// }
	//
	// private void printSelection(Monster atkPM) {
	// System.out.println("Select Move: ");
	// for (Move move : atkPM.fightMoves) {
	// if (move.equals(null))
	// System.out.println("null");
	// System.out.print(move.name + " ");
	// }
	// System.out.println("\r\nSwitch Item");
	// }
	//
	// private void onMoveMissed(Player atkPlayer, Player defPlayer, Monster
	// atkPM, Monster defPM, Move move) {
	// trainerFightMissedOutput(atkPlayer, defPlayer, atkPM, defPM, move);
	//
	// }
	//
	// private void trainerFightMissedOutput(Player atkPlayer, Player defPlayer,
	// Monster atkPM, Monster defPM, Move move) {
	// System.out.println(atkPM.name + "'s " + move.name + " missed!");
	// System.out.println(atkPlayer.name + "'s " + atkPM.name + " is at " +
	// atkPM.stats.hp + " HP");
	// System.out.println(defPlayer.name + "'s " + defPM.name + " is at " +
	// defPM.stats.hp + " HP");
	// }
	//
	// private void onMoveHit(Player atkPlayer, Player defPlayer, Monster atkPM,
	// Monster defPM, Move move) {
	//
	// String atk = atkPlayer.name + "'s ";
	// String def = defPlayer.name + "'s ";
	// if (atkPlayer.name.equals("Wild")) {
	// atk = "";
	// } else if (defPlayer.name.equals("Wild")) {
	// def = "";
	// }
	//
	// if (move.damage > 0)
	// System.out.println(move.name + " did " + calculateDamage(atkPM, defPM,
	// move) + " damage.");
	// System.out.println(atk + atkPM.name + " is at " + atkPM.stats.hp + "
	// HP");
	// if (defPM.stats.hp > calculateDamage(atkPM, defPM, move))
	// System.out.println(def + defPM.name + " is at " + (defPM.stats.hp -
	// calculateDamage(atkPM, defPM, move))
	// + " HP." + "\r\n");
	// defPM.stats.hp = (defPM.stats.hp - calculateDamage(atkPM, defPM, move));
	// applyEffects(defPM, move);
	// }
	//
}
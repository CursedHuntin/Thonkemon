package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Selection {

	private Stage stage = Startup.primaryStage;
	public static boolean sucess = true;
	public boolean play = false;
	private Stage primaryStage;
	private Scene scene;

	@FXML
	private Button Load;
	@FXML
	private Button NoLoad;
	@FXML
	private Button Fight;
	@FXML
	private Button Items;
	@FXML
	private Button Thonkemon;
	@FXML
	private Button Escape;
	@FXML
	private Button A1;
	@FXML
	private Button A2;
	@FXML
	private Button A3;
	@FXML
	private Button A4;
	@FXML
	private Button Back;

	Player p1 = new Player();
	Player p2 = new Player();

	// public Selection(Player p1, Player p2) {
	// while (true) {
	// actionSelection(p1, p2);
	// }
	// }
	@FXML
	protected void handleA1Button(ActionEvent event) {
		// Flucht
	}

	@FXML
	protected void handleA2Button(ActionEvent event) {
		// Flucht
	}

	@FXML
	protected void handleA3Button(ActionEvent event) {
		// Flucht
	}

	@FXML
	protected void handleA4Button(ActionEvent event) {
		// Flucht
	}

	@FXML
	protected void handleBackButton(ActionEvent event) {
		// Flucht
		Items.setVisible(true);
		Escape.setVisible(true);
		Thonkemon.setVisible(true);
		Fight.setVisible(true);
		Back.setDisable(true);
	}

	@FXML
	protected void handleLoadButton(ActionEvent event) {
		// hier soll das Laden einer bereits existierenden Datei implementiert
		// werden.
		Items.setVisible(true);
		Escape.setVisible(true);
		Thonkemon.setVisible(true);
		Fight.setVisible(true);
		Load.setVisible(false);
		NoLoad.setVisible(false);

	}

	@FXML
	protected void handleNoLoadButton(ActionEvent event) {
		// Neuer Spielstand
		Items.setVisible(true);
		Escape.setVisible(true);
		Thonkemon.setVisible(true);
		Fight.setVisible(true);
		Load.setVisible(false);
		NoLoad.setVisible(false);
		// while (true) {
		// actionSelection(p1, p2);
		// }
	}

	@FXML
	protected void handleFightButton(ActionEvent event) {
		// Kampf ausgew�hlt
		// new Fight(p1, p2);
		Back.setDisable(false);
		Items.setVisible(false);
		Escape.setVisible(false);
		Thonkemon.setVisible(false);
		Fight.setVisible(false);
		// open attack screen
	}

	@FXML
	protected void handleEscapeButton(ActionEvent event) {
		// Flucht
	}

	@FXML
	protected void handleItemsButton(ActionEvent event) {

	}

	@FXML
	protected void handleThonkemonButton(ActionEvent event) {
		// new TeamManagement(p1, p2);
		Items.setVisible(false);
		Escape.setVisible(false);
		Thonkemon.setVisible(false);
		Fight.setVisible(false);
		// open team selection buttons
	}

	/*
	 * public static void actionSelection(Player p1, Player p2) {
	 * System.out.println("Select: "); System.out.println("Fight Team Random");
	 * while (true) { String s = StdIn.readString(); if (s.equals("Fight")) {
	 * new Fight(p1, p2); return; } else if (s.equals("Team")) new
	 * TeamManagement(p1, p2); else if (s.equalsIgnoreCase("random")) {
	 * System.out.println("Select Player: "); System.out.println(p1.name + " " +
	 * p2.name); while (true) { s = StdIn.readString(); if (p1.name.equals(s)) {
	 * new Fight(p1); return; } else if (p2.name.equals(s)) { new Fight(p2);
	 * return; } else System.out.println("Wrong Input!"); } } else
	 * System.out.println("Wrong Input!"); } }
	 */

	public void setStage(Stage stage) {
		primaryStage = stage;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}

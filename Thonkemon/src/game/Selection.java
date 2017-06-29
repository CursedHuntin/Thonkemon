package game;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Selection {

	private Stage stage = Startup.primaryStage;
	public static boolean sucess = true;
	public boolean play = false;
	private Stage primaryStage;
	private Scene scene;
	public static File[] data;
	public static int leng = 0;
	public static int state = 0;
	public static String Item_wanted;

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
	@FXML
	private Button ItemsBack;
	@FXML
	private Button ItemsSele;

	@FXML
	private ListView<String> lv = new ListView<String>();

	@FXML
	protected void handleItemsButton(ActionEvent event) {
		lv.setLayoutX(15.0);
		lv.setLayoutY(50.0);
		if (leng == 0) {
			try {
				data = Read_items.Einlesen2("mainlist");
			} catch (FileNotFoundException e) {
				System.out.println("Beim erstllen deiner Playlist gab es einen Fehler: \n"
						+ "Entweder existiert die Liste nicht oder eine Musikdatei fehlt");
			}
			int t = 0;
			for (t = 0; t < leng; t++) {
				lv.getItems().add(new File(data[t].toString()).getName());
			}
			lv.getSelectionModel().select(0);
		}
		state = 0;
		lv.setVisible(true);
		ItemsBack.setVisible(true);
		ItemsSele.setVisible(true);
		Items.setVisible(false);
		Escape.setVisible(false);
		Thonkemon.setVisible(false);
		Fight.setVisible(false);

	}

	@FXML
	protected void handleItemsSeleButton(ActionEvent event) {
		// Flucht
		String selected_one = lv.getSelectionModel().getSelectedItem();
		for (; leng > 0; leng--) {
			lv.getItems().remove(leng - 1);
		}
		if (state == 0) {
			try {
				data = Read_items.Einlesen2(selected_one);
			} catch (FileNotFoundException e) {
				System.out.println("Beim erstllen deiner Liste gab es einen Fehler: \n"
						+ "Entweder existiert die Liste nicht oder ein element fehlt");
			}
			state++;
			int t = 0;
			for (t = 0; t < leng; t++) {
				lv.getItems().add(new File(data[t].toString()).getName());
			}
			lv.getSelectionModel().select(0);
		} else {
			Item_wanted = lv.getSelectionModel().getSelectedItem();
			// Was das Item bringen soll halt e.e
			Items.setVisible(true);
			Escape.setVisible(true);
			Thonkemon.setVisible(true);
			Fight.setVisible(true);
			lv.setVisible(false);
			ItemsBack.setVisible(false);
			ItemsSele.setVisible(false);
		}

	}

	@FXML
	protected void handleItemsBackButton(ActionEvent event) {
		// Flucht
		if (state == 0) {
			for (; leng > 0; leng--) {
				lv.getItems().remove(leng - 1);
			}
			ItemsBack.setVisible(false);
			ItemsSele.setVisible(false);
			Items.setVisible(true);
			Escape.setVisible(true);
			Thonkemon.setVisible(true);
			Fight.setVisible(true);
			lv.setVisible(false);
			lv.setLayoutX(10000.0);// visible for the win???
			lv.setLayoutY(10000.0);

		} else {
			for (; leng > 0; leng--) {
				lv.getItems().remove(leng - 1);
			}
			try {
				data = Read_items.Einlesen2("mainlist");
			} catch (FileNotFoundException e) {
				System.out.println("Beim erstllen deiner Liste gab es einen Fehler: \n"
						+ "Entweder existiert die Liste nicht oder ein element fehlt");
			}
			int t = 0;
			for (t = 0; t < leng; t++) {
				lv.getItems().add(new File(data[t].toString()).getName());
			}
			lv.getSelectionModel().select(0);
		}
		state = 0;
	}

	// Player p1 = new Player();
	// Player p2 = new Player();

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
		A1.setVisible(false);
		A2.setVisible(false);
		A3.setVisible(false);
		A4.setVisible(false);
		Back.setDisable(true);
		Back.setVisible(false);
		Items.setVisible(true);
		Escape.setVisible(true);
		Thonkemon.setVisible(true);
		Fight.setVisible(true);

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
		// Kampf ausgewählt
		// new Fight(p1, p2);
		Back.setDisable(false);
		Items.setVisible(false);
		Escape.setVisible(false);
		Thonkemon.setVisible(false);
		Fight.setVisible(false);
		A1.setVisible(true);
		A2.setVisible(true);
		A3.setVisible(true);
		A4.setVisible(true);
		Back.setVisible(true);
		// open attack screen
	}

	@FXML
	protected void handleEscapeButton(ActionEvent event) {
		// Flucht
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
